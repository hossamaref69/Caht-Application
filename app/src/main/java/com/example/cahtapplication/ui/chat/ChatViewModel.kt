package com.example.cahtapplication.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cahtapplication.base.BaseViewModel
import com.example.cahtapplication.data.repositories.rooms_repo.RoomsRepoImpl
import com.example.cahtapplication.model.Room
import com.example.cahtapplication.model.RoomMessage
import com.example.cahtapplication.model.ViewMessage
import kotlinx.coroutines.launch
import java.security.Timestamp
import java.util.concurrent.Flow

class ChatViewModel : BaseViewModel() {
    lateinit var room: Room
    var messageLiveData = MutableLiveData("")
    private var roomsRepo = RoomsRepoImpl()
    var messages = MutableLiveData<List<RoomMessage>>()

    fun senMessage() {
        viewModelScope.launch {
            if (messageLiveData.value.isNullOrEmpty()) return@launch
            try {
                roomsRepo.sendMessage(messageLiveData.value!!, room.id)
                messageLiveData.value = ""
            }catch (t: Throwable){
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = t.localizedMessage,
                    negButtonTitle = "Ok"
                )
            }
        }
    }

    fun startListening(){
        viewModelScope.launch {
            roomsRepo.startListeningToMessagesList(room.id).collect{
                messages.value = it
            }
        }
    }
}
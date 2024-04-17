package com.example.cahtapplication.ui.home.fragments.my_rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cahtapplication.base.BaseViewModel
import com.example.cahtapplication.data.repositories.rooms_repo.RoomsRepoImpl
import com.example.cahtapplication.model.Room
import com.example.cahtapplication.model.ViewMessage
import kotlinx.coroutines.launch

class MyRoomsViewModel : BaseViewModel() {
    val rooms = MutableLiveData<List<Room>>()
    private val roomsRepo = RoomsRepoImpl()

    fun refreshRooms() {
        viewModelScope.launch {
            try {
                isLoadingLiveData.value = true
                rooms.value = roomsRepo.getAllRooms()
                isLoadingLiveData.value = false
            } catch (e: Throwable) {
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    "Error",
                    e.localizedMessage,
                    posButtonTitle = "Ok"
                )
            }
        }
    }
}
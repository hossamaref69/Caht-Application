package com.example.cahtapplication.ui.create_rood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cahtapplication.base.BaseViewModel
import com.example.cahtapplication.data.repositories.rooms_repo.RoomsRepo
import com.example.cahtapplication.data.repositories.rooms_repo.RoomsRepoImpl
import com.example.cahtapplication.model.ViewMessage
import kotlinx.coroutines.launch

class RoomCreationViewModel : BaseViewModel() {

    var title = MutableLiveData("")
    var category = MutableLiveData("")
    var description = MutableLiveData("")

    var titleErrorLiveData = MutableLiveData<String?>()
    var categoryErrorLiveData = MutableLiveData<String?>()
    var descriptionErrorLiveData = MutableLiveData<String?>()
    private var roomsRepo: RoomsRepo = RoomsRepoImpl()
    var events = MutableLiveData<CreateRoomEvents>()

    fun createRoom() {
        if (!validate()) return
            viewModelScope.launch {
                try {
                    isLoadingLiveData.value = true
                    roomsRepo.createRoom(title.value!!, category.value!!, description.value!!)
                    isLoadingLiveData.value = false
                    events.value = CreateRoomEvents.RoomCreated
                } catch (e: Throwable) {
                    isLoadingLiveData.value = false
                    viewMessageLiveData.value = ViewMessage(
                        title = "Error",
                        message = e.localizedMessage,
                        posButtonTitle = "Ok"
                    )

                }
            }
    }

    private fun validate(): Boolean {
        var isValid = true

        if (title.value.isNullOrEmpty()) {
            titleErrorLiveData.value = "Please enter valid title"
            isValid = false
        } else {
            titleErrorLiveData.value = ""
        }

        if (category.value.isNullOrEmpty()) {
            categoryErrorLiveData.value = "Please enter valid category"
            isValid = false
        } else {
            categoryErrorLiveData.value = ""
        }

        if (description.value.isNullOrEmpty()) {
            descriptionErrorLiveData.value = "Please enter valid description"
            isValid = false
        } else {
            descriptionErrorLiveData.value = ""
        }
        return isValid
    }
}
package com.example.cahtapplication.ui.auth.fargments.register


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cahtapplication.base.BaseViewModel
import com.example.cahtapplication.data.repositories.auth_repo.AuthRepo
import com.example.cahtapplication.data.repositories.auth_repo.AuthRepoImpl
import com.example.cahtapplication.model.UserProvider
import com.example.cahtapplication.model.ViewMessage
import kotlinx.coroutines.launch
import com.example.cahtapplication.ui.auth.fargments.register.RegisterScreenEvents.NavigateToHomeScreen


class RegisterViewModel : BaseViewModel() {

    private var authRepo: AuthRepo = (AuthRepoImpl())
    var userNameLiveData = MutableLiveData<String>()
    var emailLiveData = MutableLiveData<String>()
    var passwordLiveData = MutableLiveData<String>()
    var userNameErrorLiveData = MutableLiveData<String?>()
    var emailErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    var events = MutableLiveData<RegisterScreenEvents>()

    fun register() {
        if (!validate()) return
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val user = authRepo.register(
                    userNameLiveData.value!!,
                    emailLiveData.value!!,
                    passwordLiveData.value!!
                )
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = NavigateToHomeScreen
            }catch (e: Exception){
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage,
                    posButtonTitle = "I have understood",
                )
            }
        }

    }

    private fun validate(): Boolean{
        var isValid = true
        if (userNameLiveData.value.isNullOrEmpty()){
            userNameErrorLiveData.value = "Please enter valid user name"
            isValid = false
        }else{
            userNameErrorLiveData.value = ""
        }

        if (emailLiveData.value.isNullOrEmpty()){
            emailErrorLiveData.value = "Please enter valid email "
            isValid = false
        }else{
            emailErrorLiveData.value = ""
        }

        if (passwordLiveData.value.isNullOrEmpty()){
            passwordErrorLiveData.value = "Please enter valid password "
            isValid = false
        }else{
            passwordErrorLiveData.value = ""
        }
        return isValid
    }

}
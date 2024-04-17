package com.example.cahtapplication.ui.auth.fargments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cahtapplication.base.BaseViewModel
import com.example.cahtapplication.data.repositories.auth_repo.AuthRepoImpl
import com.example.cahtapplication.model.UserProvider
import com.example.cahtapplication.model.ViewMessage
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    var authRepo = AuthRepoImpl()
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var emailErrorLiveData = MutableLiveData<String?>()
    var passwordErrorLiveData = MutableLiveData<String?>()
    var events = MutableLiveData<LoginScreenEvents>()


    fun login() {
        if (!validate()) return
        viewModelScope.launch {
            isLoadingLiveData.value = true
            try {
                val user = authRepo.login(emailLiveData.value!!, passwordLiveData.value!!)
                UserProvider.user = user
                isLoadingLiveData.value = false
                events.value = LoginScreenEvents.NavigateToHome
            } catch (e: Exception) {
                isLoadingLiveData.value = false
                viewMessageLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage,
                    posButtonTitle = "I have understood",
                )
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true

        if (emailLiveData.value.isNullOrEmpty()) {
            emailErrorLiveData.value = "Please enter valid email "
            isValid = false
        } else {
            emailErrorLiveData.value = ""
        }

        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordErrorLiveData.value = "Please enter valid password "
            isValid = false
        } else {
            passwordErrorLiveData.value = ""
        }
        return isValid
    }

    fun createAccountOnClick() {
        events.value = LoginScreenEvents.NavigateToRegister
    }
}
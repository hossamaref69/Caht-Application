package com.example.cahtapplication.ui.auth.fargments.login

sealed class LoginScreenEvents {

    data object NavigateToHome: LoginScreenEvents(){}

    data object NavigateToRegister: LoginScreenEvents(){}
}
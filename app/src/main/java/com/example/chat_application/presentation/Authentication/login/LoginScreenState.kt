package com.example.chat_application.presentation.Authentication.login

data class LoginScreenState(
    val isLoading: Boolean = false,
    val error:String = "",
    val navigate:Boolean = false,
    val hasInternetConnection:Boolean = false
)
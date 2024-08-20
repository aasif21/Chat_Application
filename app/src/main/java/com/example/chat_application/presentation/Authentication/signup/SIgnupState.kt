package com.example.chat_application.presentation.Authentication.signup

data class SignupState(
    val isLoading: Boolean = false,
    val error:String = "",
    val navigate:Boolean = false,
    val hasInternetConnection:Boolean = false
)
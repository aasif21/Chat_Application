package com.example.chat_application.presentation.Authentication.signup

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat_application.presentation.Authentication.login.LoginScreenState
import com.example.chat_application.util.NetworkConnectivityChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignupViewmodel: ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state: State<LoginScreenState> = _state

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")

    var isWaiting = mutableStateOf(false)

    fun checkInternetConnection(context: Context, lifecycleOwner: LifecycleOwner){
        viewModelScope.launch {
            val networkConnectivityChecker = NetworkConnectivityChecker(context)
            networkConnectivityChecker.observe(lifecycleOwner) {
                _state.value = state.value.copy(
                    hasInternetConnection = it
                )
            }
        }
    }

    fun signup(){
        viewModelScope.launch {
            try {
                isWaiting.value = true
                // delay to simulate network request
                delay(1000)
                isWaiting.value = false
            } catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

}
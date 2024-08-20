package com.example.chat_application.presentation.Authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat_application.presentation.Authentication.login.LoginComposable
import com.example.chat_application.presentation.Authentication.login.LoginScreenState
import com.example.chat_application.presentation.Authentication.login.LoginViewmodel
import com.example.chat_application.presentation.Authentication.signup.SignUpComposable
import com.example.chat_application.presentation.Authentication.signup.SignupViewmodel

@Composable
fun AuthenticationGraph(navController: NavHostController){
    val loginViewmodel = LoginViewmodel()
    val signupViewmodel = SignupViewmodel()

    NavHost(navController = navController, startDestination = AuthenticationRoutes.Login.route) {
        composable(AuthenticationRoutes.Login.route){ LoginComposable(navController = navController, viewModel = loginViewmodel, state = LoginScreenState()) }
        composable(AuthenticationRoutes.SignUp.route){ SignUpComposable(navController = navController) }
    }
}

sealed class AuthenticationRoutes(val route: String, val title: String){
    object Splash: AuthenticationRoutes(route = "splash", title = "splash route")
    object Login: AuthenticationRoutes(route = "login", title = "login route")
    object SignUp: AuthenticationRoutes(route = "signup", title = "signup route")
}
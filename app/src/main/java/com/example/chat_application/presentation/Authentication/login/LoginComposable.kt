package com.example.chat_application.presentation.Authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chat_application.presentation.Authentication.AuthenticationRoutes
import com.example.chat_application.ui.theme.primary_font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComposable(
    navController: NavHostController,
    viewModel: LoginViewmodel = androidx.lifecycle.viewmodel.compose.viewModel(),
    state: LoginScreenState
    ){

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = viewModel.state.value.hasInternetConnection) {
        viewModel.checkInternetConnection(context, lifecycleOwner)
    }

    if(!viewModel.state.value.hasInternetConnection){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Can't connect to the internet")
        }
    } else {
        // Main Column, will contain the entire screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Column containing the Text Fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.9f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Username text field
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Blue, RoundedCornerShape(12.dp)),
                    value = viewModel.username.value,
                    onValueChange = {
                        viewModel.username.value = it
                    },
                    textStyle = MaterialTheme.typography.labelSmall,
                    label = { Text("Username / Email", style = MaterialTheme.typography.labelSmall) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    )

                )
                Spacer(modifier = Modifier.height(40.dp))

                // Password text field
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Blue, RoundedCornerShape(12.dp)),
                    value = viewModel.password.value,
                    onValueChange = {
                        viewModel.password.value = it
                    },
                    label = { Text("Password", style = MaterialTheme.typography.labelSmall) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))

                // Login Button
                Button(
                    modifier = Modifier
                        .height(70.dp)
                        .width(170.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(18.dp),
                    onClick = { viewModel.login() }
                ) {
                    if(!viewModel.isWaiting.value){
                        Text(text = "Login", style = MaterialTheme.typography.labelSmall)
                    } else {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp),color = Color.White)
                    }

                }
                Spacer(modifier = Modifier.height(70.dp))
                
                // Text to navigate
                Row {
                    Text(text = "Don't have an account?  ", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(
                        text = "Sign Up",
                        color = Color.Blue,
                        fontFamily = FontFamily(Font(primary_font.value)),
                        modifier = Modifier.clickable {
                            navController.navigate(AuthenticationRoutes.SignUp.route){
                                popUpTo(AuthenticationRoutes.Login.route){inclusive = true}
                            }
                    })

                }
                
                // Button to change font
                Button(
                    modifier = Modifier
                        .height(70.dp)
                        .width(190.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(18.dp),
                    onClick = { viewModel.change_font() }
                ) {
                    Text(text = "Change Font", style = MaterialTheme.typography.labelSmall)
                }
                
            }

            // Column containing the logo at the bottom of the screen
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Blue),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Chat Application",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(primary_font.value))
                )
            }

        }
    }

}
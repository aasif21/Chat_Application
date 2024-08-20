package com.example.chat_application.presentation.Authentication.signup

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
fun SignUpComposable(
    navController: NavHostController,
    viewModel: SignupViewmodel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                    label = { Text("Username", style = MaterialTheme.typography.labelSmall) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    )

                )
                Spacer(modifier = Modifier.height(40.dp))

                // Email text field
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Blue, RoundedCornerShape(12.dp)),
                    value = viewModel.email.value,
                    onValueChange = {
                        viewModel.email.value = it
                    },
                    textStyle = MaterialTheme.typography.labelSmall,
                    label = { Text("Email", style = MaterialTheme.typography.labelSmall) },
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
                Spacer(modifier = Modifier.height(40.dp))

                // Password text field
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Blue, RoundedCornerShape(12.dp)),
                    value = viewModel.confirmPassword.value,
                    onValueChange = {
                        viewModel.confirmPassword.value = it
                    },
                    label = { Text("Confirm Password", style = MaterialTheme.typography.labelSmall) },
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
                    onClick = { viewModel.signup() }
                ) {
                    if(!viewModel.isWaiting.value){
                        Text(text = "Signup", style = MaterialTheme.typography.labelSmall)
                    } else {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp),color = Color.White)
                    }

                }
                Spacer(modifier = Modifier.height(70.dp))

                // Text to navigate
                Row {
                    Text(text = "Already have an account?  ", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(
                        text = "Login",
                        color = Color.Blue,
                        fontFamily = FontFamily(Font(primary_font.value)),
                        modifier = Modifier.clickable {
                            navController.navigate(AuthenticationRoutes.Login.route){
                                popUpTo(AuthenticationRoutes.SignUp.route){ inclusive = true }
                            }
                        })

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
package com.seinoindomobil.dev.epod.presentation.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seinoindomobil.dev.epod.R
import com.seinoindomobil.dev.epod.data.remote.dto.login.LoginDTO
import com.seinoindomobil.dev.epod.presentation.theme.Blue500
import com.seinoindomobil.dev.epod.presentation.theme.Poppins
import com.seinoindomobil.dev.epod.presentation.ui.login.component.LoginState

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordIsVisible by remember { mutableStateOf(false) }

    val isFormValid by derivedStateOf { username.isNotBlank() && password.isNotBlank() && password.length < 9 }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Image(
            painter = painterResource(id = R.drawable.background_login),
            contentDescription = "background_login",
            Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(Modifier.height(100.dp))
            Row(
                Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo_splash),
                    contentDescription = "Logo",
                    Modifier
                        .size(80.dp)
                )
                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.IntroText),
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.SubIntroText),
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Thin,
                        color = Color.White,
                        fontSize = 13.sp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.Epod),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = stringResource(id = R.string.EpodComplete),
                            fontSize = 11.sp,
                            color = Color.White,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Thin
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))

            Card(Modifier.weight(weight = 2f), shape = RoundedCornerShape(18.dp, 18.dp)) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(Modifier.height(40.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = username,
                        singleLine = true,
                        onValueChange = { username = it },
                        label = { Text(text = "Username") },
                        trailingIcon = {
                            IconButton(onClick = { username = "" }) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                            }
                        },
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        singleLine = true,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (passwordIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordIsVisible = !passwordIsVisible }) {
                                Icon(
                                    imageVector = if (passwordIsVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                    )
                    Spacer(Modifier.height(60.dp))
                    Button(
                        onClick = {
                            viewModel.login(username,password)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = isFormValid,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Blue500)
                    ) {
                        Text(
                            text = "Login",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (viewModel.loginState.isLoading) {
                            CircularProgressIndicator()
                        }
                        if (viewModel.loginState.error != null) {
                            Toast.makeText(LocalContext.current, viewModel.loginState.error, Toast.LENGTH_LONG)
                                .show()
                        }
                        if (viewModel.loginState.login != null) {
                            LaunchedEffect(key1 = viewModel.loginState) {
                                navController.navigate("home_screen")
                               viewModel.saveToken(LoginState().login?.token.toString())
                                Log.d("TAG", "LoginScreen: ${viewModel.saveToken(LoginState().login?.token.toString())}")
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.seinoindomobil.dev.epod.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seinoindomobil.dev.epod.presentation.ui.splashscreen.SplashViewModel

@Composable
fun HomeScreen(navController: NavController,splashViewModel: SplashViewModel = hiltViewModel()) {
    val token = splashViewModel.startDestination.value.token
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Order pAGE")
    }
}
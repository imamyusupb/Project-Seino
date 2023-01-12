package com.seinoindomobil.dev.epod.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Hello World")
    }
}
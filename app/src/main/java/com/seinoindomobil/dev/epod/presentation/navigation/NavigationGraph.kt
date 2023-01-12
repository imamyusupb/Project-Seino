package com.seinoindomobil.dev.epod.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seinoindomobil.dev.epod.presentation.ui.HomeScreen
import com.seinoindomobil.dev.epod.presentation.ui.splashscreen.SplashScreen
import com.seinoindomobil.dev.epod.presentation.ui.login.LoginScreen
import com.seinoindomobil.dev.epod.presentation.ui.login.LoginViewModel
import com.seinoindomobil.dev.epod.presentation.ui.onboarding.OnBoardingScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController,)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.OnBoardingScreen.route){
            OnBoardingScreen(navController = navController)
        }
    }
}
package com.seinoindomobil.dev.epod.presentation.navigation

sealed class Screen(val route :String){
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object LoginScreen : Screen("login_screen")
    object OnBoardingScreen : Screen("onboarding_screen")

}

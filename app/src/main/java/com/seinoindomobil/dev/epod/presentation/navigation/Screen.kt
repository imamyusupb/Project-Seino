package com.seinoindomobil.dev.epod.presentation.navigation

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object OnBoardingScreen : Screen("onboarding_screen")
    object LoginScreen : Screen("login_screen")
    object DashboardScreen : Screen("dashboard_screen")
    object OrderScreen : Screen("order_screen")
    object ProfileScreen : Screen("profile_screen")
    object HomeScreen : Screen("home_screen")
    object DashboardFeatureScreen : Screen("feature_screen")

}

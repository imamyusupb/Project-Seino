package com.seinoindomobil.dev.epod.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.seinoindomobil.dev.epod.presentation.ui.HomeScreen
import com.seinoindomobil.dev.epod.presentation.ui.component.AppBottomBar
import com.seinoindomobil.dev.epod.presentation.ui.component.AppFloatingActionBar
import com.seinoindomobil.dev.epod.presentation.ui.component.AppScaffold
import com.seinoindomobil.dev.epod.presentation.ui.dashboard.DashboardScreen
import com.seinoindomobil.dev.epod.presentation.ui.login.LoginScreen
import com.seinoindomobil.dev.epod.presentation.ui.onboarding.OnBoardingScreen
import com.seinoindomobil.dev.epod.presentation.ui.profile.ProfileScreen
import com.seinoindomobil.dev.epod.presentation.ui.splashscreen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(startDestination: String = Screen.SplashScreen.route) {

    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AppScaffold(
        bottomBar = {
            BottomBarItem.values().forEach { navItem ->

                if (navItem.route == currentRoute) {
                    AppBottomBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        },
        floatingActionButton = {
            BottomBarItem.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    AppFloatingActionBar(navController = navController)
                }
            }
        },
        backgroundColor = Color.White,
        content = { innerPadding ->
            AnimatedNavHost(
                navController = navController,
                startDestination = startDestination,
                Modifier.padding(innerPadding)
            ) {
                composable(Screen.SplashScreen.route) {
                    SplashScreen(navController)
                }
                composable(Screen.DashboardScreen.route) {
                    DashboardScreen(navController = navController)
                }
                composable(Screen.LoginScreen.route) {
                    LoginScreen(navController = navController)
                }
                composable(Screen.OnBoardingScreen.route) {
                    OnBoardingScreen(navController)
                }
                composable(Screen.HomeScreen.route) {
                    HomeScreen(navController = navController)
                }
                composable(route = Screen.ProfileScreen.route) {
                    ProfileScreen(navController = navController)
                }
                composable(route = Screen.OrderScreen.route) {
                    HomeScreen(navController = navController)
                }
            }
        }
    )
}

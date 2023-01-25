package com.seinoindomobil.dev.epod.presentation.navigation

import com.seinoindomobil.dev.epod.R

enum class BottomBarItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    DASHBOARD(
        "Dashboard",
        R.drawable.ic_dashboard,
        Screen.DashboardScreen.route
    ),
    ORDER(
        "Order",
        R.drawable.ic_transparant,
        Screen.OrderScreen.route
    ),
    PROFILE(
        "Profile",
        R.drawable.ic_profile,
        Screen.ProfileScreen.route
    )
}
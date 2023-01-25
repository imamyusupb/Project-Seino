package com.seinoindomobil.dev.epod.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.seinoindomobil.dev.epod.presentation.navigation.BottomBarItem
import com.seinoindomobil.dev.epod.presentation.navigation.Screen
import com.seinoindomobil.dev.epod.presentation.theme.Blue600

@Composable
fun AppBottomBar(
    navController: NavController,
    currentRoute :String?
) {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            ),
        elevation = 20.dp,
        backgroundColor = Color.White
    ) {

        BottomBarItem.values().forEach { screen ->

            BottomNavigationItem(
                alwaysShowLabel = true,
                selectedContentColor = Blue600,
                unselectedContentColor = if (screen.title == "Order") Color(0x0000ffff ) else Color(0xFFAAAAAA),
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.icon),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },

                label = {
                    Text(
                        text = if (screen.title == "Order") "" else screen.title,
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.Center
                    )
                },
                selected = currentRoute == screen.title,
                onClick = {
                    if (currentRoute == screen.route) {
                        return@BottomNavigationItem
                    }

                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            Screen.DashboardScreen.route.let { dashboardRoute ->
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
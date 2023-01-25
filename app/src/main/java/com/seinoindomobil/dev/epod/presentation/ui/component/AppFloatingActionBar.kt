package com.seinoindomobil.dev.epod.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.seinoindomobil.dev.epod.core.util.Animation.getAnimateFloat
import com.seinoindomobil.dev.epod.presentation.navigation.Screen
import com.seinoindomobil.dev.epod.presentation.theme.Blue600
import kotlinx.coroutines.delay
import com.seinoindomobil.dev.epod.R.*

@Composable
fun AppFloatingActionBar(
    navController: NavController
) {
    var isClick by remember { mutableStateOf(false) }

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(800)
            isClick = false
        }
    }

    FloatingActionButton(
        onClick = {
            isClick = true
            navController.navigate(Screen.OrderScreen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            navController.navigate(Screen.OrderScreen.route)
        },
        contentColor = Color.White ,
        backgroundColor = Blue600,
        modifier = Modifier.size(60.dp)
    ) {
        Icon(
            painter = painterResource(id = drawable.ic_order),
            contentDescription = "icon order",
            modifier = Modifier.size(30.dp)
        )
    }
}
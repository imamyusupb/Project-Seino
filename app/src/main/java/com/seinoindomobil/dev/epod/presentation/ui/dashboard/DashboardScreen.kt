package com.seinoindomobil.dev.epod.presentation.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seinoindomobil.dev.epod.R
import com.seinoindomobil.dev.epod.presentation.theme.Poppins
import com.seinoindomobil.dev.epod.presentation.ui.component.AppScaffold

@Composable
fun DashboardScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    AppScaffold(Modifier.fillMaxSize()) {
//        TopSectionDashboard(dashboardViewModel = dashboardViewModel)
    }
}


@Composable
fun TopSectionDashboard(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_splash),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text(
                text = dashboardViewModel.username.value,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(text = dashboardViewModel.company.value, fontFamily = Poppins, fontSize = 12.sp)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .weight(2f)
                .padding(end = 20.dp)
        )
    }
}
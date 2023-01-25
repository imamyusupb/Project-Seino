package com.seinoindomobil.dev.epod.presentation.ui.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.seinoindomobil.dev.epod.R
import com.seinoindomobil.dev.epod.presentation.theme.Poppins
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    var startAnimation by remember { mutableStateOf(false) }


    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 0.8f else 0f,
        animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate("profile_screen")
//        navController.navigate(splashViewModel.startDestination.value.destination.toString())

    }
    Splash(scale = alphaAnim.value)
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
fun Splash(scale: Float = 0f) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_splash),
            contentDescription = "back-ground",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_splash),
                contentDescription = "logo_splash",
                modifier = Modifier.scale(scale)
            )
            Text(
                stringResource(id = R.string.TitleSplash),
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.alpha(scale)
            )
            Text(
                stringResource(id = R.string.SubTitleSplash),
                fontFamily = Poppins,
                fontWeight = FontWeight.Thin,
                fontSize = 14.sp,
                color = Color.White, modifier = Modifier.alpha(scale)

            )
        }
        Text(
            stringResource(id = R.string.CreatorSplash),
            fontFamily = Poppins,
            fontWeight = FontWeight.Thin,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }
}
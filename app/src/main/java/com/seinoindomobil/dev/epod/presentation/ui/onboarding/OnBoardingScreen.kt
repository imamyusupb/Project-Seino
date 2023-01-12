package com.seinoindomobil.dev.epod.presentation.ui.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import com.seinoindomobil.dev.epod.R.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.seinoindomobil.dev.epod.core.util.AppDatastore
import com.seinoindomobil.dev.epod.domain.model.OnBoarding
import com.seinoindomobil.dev.epod.presentation.theme.Blue500
import com.seinoindomobil.dev.epod.presentation.theme.Grey
import com.seinoindomobil.dev.epod.presentation.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val items = OnBoarding.getData()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val context = LocalContext.current
    val appDatastore = AppDatastore.getInstance(context)!!


    Column(modifier = Modifier.fillMaxSize()) {
        TopBoardSection(navController = navController)

        HorizontalPager(
            count = items.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) { page ->
            HorizontalBoardPager(items = items[page])
        }

        BottomBoardingSection(
            size = items.size,
            index = pagerState.currentPage,
            onBackClick = {
                if (pagerState.currentPage + 1 > 1) scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage - 1)
                }
            },
            onButtonClick = {
                if (pagerState.currentPage + 1 < items.size) scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
                else {
                    scope.launch {
                        appDatastore.saveOnBoardStatus(true)
                    }
                    navController.navigate("login_screen")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBoardSection(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(12.dp).fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = drawable.img_epod),
            contentDescription = "logo",
            modifier.size(width = 80.dp, height = 50.dp)
        )
        Text(text = "Masuk", style = TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold
        ), modifier = Modifier.clickable {
            navController.navigate("login_screen")
        })
    }
}

@Composable
fun HorizontalBoardPager(items: OnBoarding) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = "image",
            modifier = Modifier.padding(start = 50.dp, end = 50.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(id = items.description),
            // fontSize = 24.sp,
            color = Color.Black,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) Blue500 else Grey
            )
    ) {

    }
}

@Composable
fun BottomBoardingSection(
    size: Int,
    index: Int,
    onBackClick: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-20).dp)
            .padding(12.dp)
    ) {
        TextButton(onClick = onBackClick, modifier = Modifier.align(Alignment.BottomStart)) {
            val buttontext = if (index == 0) "" else "Kembali"
            Text(
                text = buttontext, color = Grey,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
            )
        }
        Indicators(size = size, index = index)

        TextButton(onClick = onButtonClick, modifier = Modifier.align(Alignment.BottomEnd)) {
            val buttontext = if (size == index + 1) "Mulai" else "Lanjut"
            Text(
                text = buttontext,
                color = Blue500,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


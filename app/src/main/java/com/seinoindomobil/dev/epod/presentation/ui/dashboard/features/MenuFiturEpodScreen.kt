package com.seinoindomobil.dev.epod.presentation.ui.dashboard.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.seinoindomobil.dev.epod.domain.model.FiturDashboardItem
import com.seinoindomobil.dev.epod.domain.model.dummyFiturDashboardItem
import com.seinoindomobil.dev.epod.presentation.theme.Blue500
import com.seinoindomobil.dev.epod.presentation.theme.Blue600
import com.seinoindomobil.dev.epod.presentation.theme.Poppins



@Composable
fun MenuFiturScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Menu Fitur E-Pod")
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
            Spacer(modifier = Modifier.width(100.dp))
        }, backgroundColor = Blue600, contentColor = Color.White, elevation = 10.dp
        )
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(top = 20.dp, start = 16.dp)) {
                Text(
                    text = "Fitur Lengkap E-Pod", style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "Ketahui seluruh menu fitur yang tersedia pada aplikasi E-Pod",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Thin,
                        fontSize = 12.sp,
                    ),
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 16.dp)
                )

                MenuFiturItemList(list = dummyFiturDashboardItem)
            }
        }

    })
}

@Composable
fun MenuFiturItemList(list: List<FiturDashboardItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list, key = { it.title!!}) { item ->
            MenuFiturItem(item)
        }
    }
}

@Composable
fun MenuFiturItem(
    item: FiturDashboardItem
) {
    Card(
        modifier = Modifier
            .padding(top = 30.dp, end = 28.dp)
            .fillMaxWidth()
            .shadow(4.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 16.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
                    painter = painterResource(id = item.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(
                        text = item.title.toString(), style = TextStyle(
                            fontSize = 9.5.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = item.subTitle.toString(), style = TextStyle(
                            fontSize = 6.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Thin
                        ), color = Color.Gray
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end=26.dp)) {
                OutlinedButton(
                    modifier = Modifier
                        .width(85.dp)
                        .height(20.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue500),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = item.buttonTitle.toString(), style = TextStyle(
                            fontSize = 8.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
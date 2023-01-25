package com.seinoindomobil.dev.epod.presentation.ui.dashboard.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seinoindomobil.dev.epod.domain.model.NotificationItem
import com.seinoindomobil.dev.epod.domain.model.dummyNotification
import com.seinoindomobil.dev.epod.presentation.theme.Blue600
import com.seinoindomobil.dev.epod.presentation.theme.Poppins

@Preview(showBackground = true)
@Composable
fun NotifcationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notifikasi")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                    Spacer(modifier = Modifier.width(100.dp))
                },
                backgroundColor = Blue600,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                       NotificationItemList(notif = dummyNotification)

            }

        })
}

@Composable
fun NotificationItemList(notif: List<NotificationItem>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(notif,key = {it.title!!}){ item -> NotificationItem(notif = item)
            Divider(color = Color.Gray)
        }
    }
}

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notif:NotificationItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = notif.image),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 26.dp)
                .size(36.dp),
            contentScale = ContentScale.Fit
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = notif.title.toString(), style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = notif.date.toString(), style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Thin
                    ),
                    color = Color.Gray
                )
            }

            Text(
                text = notif.description.toString(), style = TextStyle(
                    fontFamily = Poppins,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Thin
                ), modifier = Modifier.padding(end = 60.dp)
            )
        }

    }
}


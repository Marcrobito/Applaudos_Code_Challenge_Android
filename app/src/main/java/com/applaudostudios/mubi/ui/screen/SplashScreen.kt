package com.applaudostudios.mubi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.applaudostudios.mubi.R
import com.applaudostudios.mubi.ui.theme.LightBlue
import com.applaudostudios.mubi.ui.theme.PrimaryPurple

@Composable
fun SplashScreen() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightBlue, PrimaryPurple, PrimaryPurple)
                )
            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painterResource(R.drawable.logo_splash),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.6F),
            contentScale = ContentScale.FillWidth
        )
    }
}
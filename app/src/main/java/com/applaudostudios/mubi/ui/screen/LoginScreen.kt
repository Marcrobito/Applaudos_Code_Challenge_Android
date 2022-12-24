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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.R
import com.applaudostudios.mubi.ui.component.MubiButton
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.HOME_ROUTE_STRING

@Composable
fun LoginScreen(navController: NavHostController? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.logo_signin),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.4F),
            contentScale = ContentScale.FillWidth
        )
        VerticalSpace(48)
        MubiButton(
            text = stringResource(id = R.string.sign_in),
            onClick = { navController?.navigate(HOME_ROUTE_STRING) }
        )
    }
}
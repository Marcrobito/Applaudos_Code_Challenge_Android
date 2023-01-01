package com.applaudostudios.mubi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.R
import com.applaudostudios.mubi.ui.navigation.HOME_ROUTE_STRING
import com.applaudostudios.mubi.ui.navigation.LOGIN_ROUTE_STRING
import com.applaudostudios.mubi.ui.navigation.SIGN_IN_ROUTE_STRING
import com.applaudostudios.mubi.ui.screen.splash.SplashViewModel
import com.applaudostudios.mubi.ui.theme.LightBlue
import com.applaudostudios.mubi.ui.theme.Purple

@Composable
fun SplashScreen(
    navController: NavHostController? = null,
    splashViewModel: SplashViewModel = viewModel()
) {
    val state = splashViewModel.state.collectAsState()
    if (state.value.isUserAuthenticated) {
        navController?.navigate(HOME_ROUTE_STRING)
    } else {
        navController?.navigate(SIGN_IN_ROUTE_STRING)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightBlue, Purple, Purple)
                )
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.logo_splash),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.6F),
            contentScale = ContentScale.FillWidth
        )
    }
}
package com.applaudostudios.mubi.ui.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.applaudostudios.mubi.ui.component.TextWithLink
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.LOGIN_ROUTE_STRING
import com.applaudostudios.mubi.ui.navigation.REGISTER_ROUTE_STRING
import com.applaudostudios.mubi.ui.theme.MubiPalette

@Composable
fun SignInScreen(
    navController: NavHostController? = null
) {
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
        VerticalSpace(32)
        Text(
            text = "Sign in to your account",
            style = MaterialTheme.typography.body1,
            color = MubiPalette.Text
        )
        VerticalSpace(32)
        MubiButton(
            text = stringResource(id = R.string.sign_in),
            onClick = {
                navController?.navigate(LOGIN_ROUTE_STRING)
            }
        )
        VerticalSpace(8)
        TextWithLink(
            fullText = "Not have an account? Register",
            linkText = Pair("Register") {
                navController?.navigate(REGISTER_ROUTE_STRING)
            }
        )
    }
}

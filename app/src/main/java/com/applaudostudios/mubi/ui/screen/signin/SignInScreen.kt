package com.applaudostudios.mubi.ui.screen.signin

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.R
import com.applaudostudios.mubi.ui.component.MubiButton
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.HOME_ROUTE_STRING
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials

@Composable
fun SignInScreen(
    navController: NavHostController? = null
) {
    val context = LocalContext.current
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
            onClick = {
                navController?.navigate(HOME_ROUTE_STRING)
            }
        )
    }
}

private fun loginWithBrowser(context: Context) {
    val account = Auth0(context)
    WebAuthProvider.login(account)
        .withScheme("demo")
        .withScope("openid profile email")

        // Launch the authentication passing the callback where the results will be received
        .start(context, object : Callback<Credentials, AuthenticationException> {
            // Called when there is an authentication failure
            override fun onFailure(exception: AuthenticationException) {
                // Something went wrong!
                Log.e("Error", exception.message?:"Error")
            }

            // Called when authentication completed successfully
            override fun onSuccess(credentials: Credentials) {
                // Get the access token from the credentials object.
                // This can be used to call APIs
                val accessToken = credentials.accessToken
            }
        })
}
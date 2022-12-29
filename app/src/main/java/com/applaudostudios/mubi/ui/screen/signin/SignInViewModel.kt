package com.applaudostudios.mubi.ui.screen.signin

import android.app.Application
import android.util.Log
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.Action
import com.applaudostudios.mubi.mvi.state.SeriesDetailState
import com.applaudostudios.mubi.mvi.state.State
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.auth0.android.callback.Callback

@HiltViewModel
class SignInViewModel @Inject constructor(context: Application) : BaseViewModel<Action, State>() {

    override val initialState: State = SeriesDetailState()

    private val account = Auth0(context)

    init {
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

    override fun userInput(input: Action) {

    }
}
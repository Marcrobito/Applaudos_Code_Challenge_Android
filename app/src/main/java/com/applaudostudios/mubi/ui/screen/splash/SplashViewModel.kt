package com.applaudostudios.mubi.ui.screen.splash

import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.Action
import com.applaudostudios.mubi.mvi.state.SplashState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel : BaseViewModel<Action, SplashState>() {

    private val isUserAuthenticated = Firebase.auth.currentUser != null

    override val initialState: SplashState = SplashState(isUserAuthenticated = isUserAuthenticated)

    override fun userInput(input: Action) {
        return
    }

}
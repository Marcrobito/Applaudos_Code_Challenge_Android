package com.applaudostudios.mubi.ui.screen.profile

import androidx.lifecycle.viewModelScope
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.ProfileAction
import com.applaudostudios.mubi.mvi.state.LogOutState
import com.applaudostudios.mubi.mvi.state.ProfileState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel<ProfileAction, ProfileState>() {

    private val auth = Firebase.auth

    override val initialState: ProfileState = ProfileState()

    override fun userInput(input: ProfileAction) {
        when (input) {
            is ProfileAction.LogOut -> _state.value =
                _state.value.copy(logOutState = LogOutState.LogOutRequested)
            is ProfileAction.LogOutCancel ->_state.value =
                _state.value.copy(logOutState = null)
            is ProfileAction.LogOutConfirmation -> {
                auth.signOut()
            }
        }
    }
}
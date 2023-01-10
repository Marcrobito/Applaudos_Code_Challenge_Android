package com.applaudostudios.mubi.mvi.state

import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.core.domain.model.TVListType.TOP_RATED

sealed class State

data class SplashState(
    val isUserAuthenticated: Boolean = false
) : State()

data class RegisterState(
    val isLoading: Boolean = false,
    val isRegisterSuccess: Boolean? = null,
    val error: String? = null
) : State()

data class LoginState(
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean? = null,
    val error: String? = null
) : State()

data class HomeState(
    val isLoading: Boolean = true,
    val data: List<Card> = emptyList(),
    val tvType: TVListType = TOP_RATED,
    val error: String? = null,
    val page: Int = 0,
    val pages: Int = 0
) : State()

data class SearchState(
    val isLoading: Boolean = false,
    val data: List<Card> = emptyList(),
    val query: String? = null,
    val error: String? = null,
    val page: Int = 0,
    val pages: Int = 0
) : State()

sealed class LogOutState : State() {
    object LogOutRequested : LogOutState()
    object LogOutConfirmed : LogOutState()
}

data class ProfileState(val logOutState: LogOutState? = null) : State()

data class SeriesDetailState(
    val isLoading: Boolean = true,
    val data: Show? = null,
    val error: String? = null
) : State()



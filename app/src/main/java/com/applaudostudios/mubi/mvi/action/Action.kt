package com.applaudostudios.mubi.mvi.action

import com.applaudostudios.core.domain.model.TVListType

sealed class Action

sealed class RegisterAction : Action() {
    data class RegisterUser(
        val email: String,
        val emailConfirmation: String,
        val password: String,
        val passwordConfirmation: String
    ) : RegisterAction()
}

sealed class LoginAction : Action() {
    data class LoginUser(
        val email: String,
        val password: String,
    ) : LoginAction()
}

sealed class HomeAction : Action() {
    data class NavigateToList(val tvListType: TVListType) : HomeAction()
    data class PresentTVDetail(val id: Int) : HomeAction()
}

sealed class ProfileAction : Action() {
    object LogOut : ProfileAction()
    object LogOutCancel : ProfileAction()
    object LogOutConfirmation : ProfileAction()
}

sealed class SeriesDetailAction : Action() {
    data class ShowSeriesDetail(val seriesId: Int) : SeriesDetailAction()
}
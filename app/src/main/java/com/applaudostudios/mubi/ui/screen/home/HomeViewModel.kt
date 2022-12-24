package com.applaudostudios.mubi.ui.screen.home

import com.applaudostudios.mubi.BaseViewModel
import com.applaudostudios.mubi.mvi.action.HomeAction
import com.applaudostudios.mubi.mvi.action.HomeAction.NavigateToList
import com.applaudostudios.mubi.mvi.action.HomeAction.PresentTVDetail
import com.applaudostudios.mubi.mvi.state.HomeState


class HomeViewModel : BaseViewModel<HomeAction, HomeState>() {
    override val initialState: HomeState = HomeState()

    override fun userInput(input: HomeAction) = with(input) {
        when(this){
            is NavigateToList -> {}
            is PresentTVDetail -> {}
        }
    }
}
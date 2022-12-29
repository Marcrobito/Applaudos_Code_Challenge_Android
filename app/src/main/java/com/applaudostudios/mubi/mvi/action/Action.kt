package com.applaudostudios.mubi.mvi.action

import com.applaudostudios.core.domain.model.TVListType

sealed class Action

sealed class HomeAction : Action() {
    data class NavigateToList(val tvListType: TVListType) : HomeAction()
    data class PresentTVDetail(val id: Int) : HomeAction()
}

sealed class SeriesDetailAction : Action() {
    data class ShowSeriesDetail(val seriesId: Int) : SeriesDetailAction()
}
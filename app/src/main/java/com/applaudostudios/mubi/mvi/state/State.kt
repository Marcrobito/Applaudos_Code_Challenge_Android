package com.applaudostudios.mubi.mvi.state

import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.core.domain.model.TVListType.TOP_RATED

sealed class State

data class HomeState(
    val isLoading: Boolean = true,
    val data: List<Card> = emptyList(),
    val tvType: TVListType = TOP_RATED,
    val error: String? = null
) : State()
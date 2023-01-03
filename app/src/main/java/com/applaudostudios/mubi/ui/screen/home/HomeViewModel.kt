package com.applaudostudios.mubi.ui.screen.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.core.usecases.GetTVList
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.HomeAction
import com.applaudostudios.mubi.mvi.action.HomeAction.NavigateToList
import com.applaudostudios.mubi.mvi.action.HomeAction.PresentTVDetail
import com.applaudostudios.mubi.mvi.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getTVList: GetTVList) :
    BaseViewModel<HomeAction, HomeState>() {
    override val initialState: HomeState = HomeState()

    init {
        loadShowList()
    }

    override fun userInput(input: HomeAction) {
        when (input) {
            is NavigateToList -> loadShowList(input.tvListType)
            is PresentTVDetail -> {}
        }
    }

    private fun loadShowList(tvListType: TVListType = TVListType.TOP_RATED) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val response = getTVList.invoke(tvListType)

            if (response is Response.Success) {
                val data =
                    if (tvListType == _state.value.tvType)
                        _state.value.data + response.data
                    else
                        response.data
                _state.value = _state.value.copy(
                    isLoading = false,
                    data = data,
                    tvType = tvListType
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = (response as Response.Error).error.message
                )
            }
        }
    }
}
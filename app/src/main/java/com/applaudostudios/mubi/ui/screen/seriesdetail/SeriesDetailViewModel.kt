package com.applaudostudios.mubi.ui.screen.seriesdetail

import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response.Error
import com.applaudostudios.core.domain.model.Response.Success
import com.applaudostudios.core.usecases.GetShow
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.SeriesDetailAction
import com.applaudostudios.mubi.mvi.action.SeriesDetailAction.ShowSeriesDetail
import com.applaudostudios.mubi.mvi.state.SeriesDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesDetailViewModel @Inject constructor(private val getShow: GetShow) :
    BaseViewModel<SeriesDetailAction, SeriesDetailState>() {

    override val initialState: SeriesDetailState = SeriesDetailState()

    override fun userInput(input: SeriesDetailAction) {
        when (input) {
            is ShowSeriesDetail -> loadSeriesDetail(input.seriesId)
        }
    }

    private fun loadSeriesDetail(id: Int) {
        _state.value = _state.value.copy(isLoading = true, data = null, error = null)
        viewModelScope.launch {
            val result = getShow(id)
            if (result is Success) {
                _state.value =
                    _state.value.copy(isLoading = false, data = result.data, error = null)
            } else if (result is Error) {
                _state.value =
                    _state.value.copy(
                        isLoading = false,
                        data = null,
                        error = result.error.message
                    )
            }
        }
    }

}
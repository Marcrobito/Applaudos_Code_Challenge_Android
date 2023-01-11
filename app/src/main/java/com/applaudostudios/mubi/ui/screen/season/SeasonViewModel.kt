package com.applaudostudios.mubi.ui.screen.season

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.usecases.GetSeason
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.SeasonAction
import com.applaudostudios.mubi.mvi.state.SeasonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(private val getSeason: GetSeason):BaseViewModel<SeasonAction,SeasonState>() {
    override val initialState: SeasonState = SeasonState()

    override fun userInput(input: SeasonAction) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = getSeason.invoke(seasonNumber = input.seasonId, seriesId = input.seriesId)
            if(result is Response.Success)
                _state.value = _state.value.copy(isLoading = false, error = null, data = result.data)
            if(result is Response.Error)
                _state.value = _state.value.copy(isLoading = false, error = result.error.message?:"Some error", data = null)
            Log.d("res,", _state.value.toString())
            Log.d("res,", result.toString())
        }
    }
}
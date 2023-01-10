package com.applaudostudios.mubi.ui.screen.search

import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.usecases.SearchTVShow
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.mvi.action.SearchAction
import com.applaudostudios.mubi.mvi.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchTVShow: SearchTVShow) :
    BaseViewModel<SearchAction, SearchState>() {
    override val initialState = SearchState()

    override fun userInput(input: SearchAction) {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            _state.value = when (val result = searchTVShow.invoke(input.query)) {
                is Response.Success -> {
                    val data =
                        if (input.query == _state.value.query)
                            _state.value.data + result.data
                        else
                            result.data
                    _state.value.copy(
                        isLoading = false,
                        error = null,
                        data = data,
                        query = input.query
                    )
                }
                is Response.Error -> _state.value.copy(
                    isLoading = false,
                    error = result.error.message ?: "Some error"
                )

            }
        }
    }
}
package com.applaudostudios.mubi

import androidx.lifecycle.ViewModel
import com.applaudostudios.mubi.mvi.action.Action
import com.applaudostudios.mubi.mvi.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<in T: Action, out U: State>: ViewModel() {
    protected abstract val initialState: U
    private val _state:MutableStateFlow<U> by lazy { MutableStateFlow(initialState) }
    val state : StateFlow<U?> get() = _state

    abstract fun userInput(input:T)
}
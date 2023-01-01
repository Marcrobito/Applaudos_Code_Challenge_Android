package com.applaudostudios.mubi.ui.screen.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.base.isNotValidEmail
import com.applaudostudios.mubi.mvi.action.RegisterAction
import com.applaudostudios.mubi.mvi.state.RegisterState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : BaseViewModel<RegisterAction, RegisterState>() {
    override val initialState: RegisterState = RegisterState()

    private val auth = Firebase.auth

    override fun userInput(input: RegisterAction) {
        (input as? RegisterAction.RegisterUser)?.let { registerUser ->
            when {
                registerUser.email.isNotValidEmail() -> _state.value =
                    _state.value.copy(error = "Email is not valid")
                registerUser.email != registerUser.emailConfirmation -> _state.value =
                    _state.value.copy(error = "Email mismatch")
                registerUser.password.isBlank() || registerUser.password.length < 6 -> _state.value =
                    _state.value.copy(error = "Invalid password, must be as least 6 characters long")
                registerUser.password != registerUser.passwordConfirmation -> _state.value =
                    _state.value.copy(error = "Password mismatch")
                else -> {
                    _state.value =
                        _state.value.copy(isLoading = true, error = null, isRegisterSuccess = null)
                    viewModelScope.launch {
                        val result = signUpUser(registerUser.email, registerUser.password)

                        (result as? Response.Success)?.let {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = null,
                                isRegisterSuccess = true
                            )
                            Log.d("VM", auth.currentUser?.uid.toString())
                        }
                        (result as? Response.Error)?.let { result ->
                        _state.value =
                                _state.value.copy(
                                    isLoading = false,
                                    error = result.error.message?:"Unable to Register Please try again later",
                                    isRegisterSuccess = false
                                )
                        }
                    }
                }
            }
        }
    }

    private suspend fun signUpUser(email: String, password: String): Response<AuthResult> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
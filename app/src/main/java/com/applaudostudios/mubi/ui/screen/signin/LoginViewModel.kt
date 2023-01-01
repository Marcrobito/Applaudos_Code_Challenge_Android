package com.applaudostudios.mubi.ui.screen.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.mubi.base.BaseViewModel
import com.applaudostudios.mubi.base.isNotValidEmail
import com.applaudostudios.mubi.mvi.action.LoginAction
import com.applaudostudios.mubi.mvi.state.LoginState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : BaseViewModel<LoginAction, LoginState>() {
    override val initialState: LoginState = LoginState()

    private val auth = Firebase.auth

    override fun userInput(input: LoginAction) {
        (input as? LoginAction.LoginUser)?.let { loginUser ->
            when {
                loginUser.email.isNotValidEmail() -> _state.value =
                    _state.value.copy(error = "Email is not valid")
                loginUser.password.isBlank() || loginUser.password.length < 6 -> _state.value =
                    _state.value.copy(error = "Invalid password, must be as least 6 characters long")
                else -> {

                    _state.value =
                        _state.value.copy(isLoading = true, error = null, isLoginSuccess = null)

                    viewModelScope.launch {
                        val result = signInUser(loginUser.email, loginUser.password)

                        (result as? Response.Success)?.let {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = null,
                                isLoginSuccess = true
                            )
                            Log.d("VM", auth.currentUser?.uid.toString())
                        }
                        (result as? Response.Error)?.let { result ->
                            _state.value =
                                _state.value.copy(
                                    isLoading = false,
                                    error = result.error.message
                                        ?: "Unable to Register Please try again later",
                                    isLoginSuccess = false
                                )
                        }
                    }
                }
            }
        }
    }

    private suspend fun signInUser(email: String, password: String): Response<AuthResult> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
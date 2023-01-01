package com.applaudostudios.mubi.ui.screen.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.mvi.action.RegisterAction
import com.applaudostudios.mubi.ui.component.CustomTextField
import com.applaudostudios.mubi.ui.component.Loader
import com.applaudostudios.mubi.ui.component.MubiButton
import com.applaudostudios.mubi.ui.component.MubiButtonStyle
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.HOME_ROUTE_STRING

@Composable
fun RegisterScreen(
    navController: NavHostController? = null,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val state = registerViewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var emailConfirmation by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Register") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        contentPadding
        Box {
            Column(
                modifier = Modifier.padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    placeholder = "Email",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    }
                )
                VerticalSpace(16)
                CustomTextField(
                    value = emailConfirmation,
                    onValueChange = {
                        emailConfirmation = it
                    },
                    placeholder = "Email Confirmation",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    }
                )
                VerticalSpace(16)
                CustomTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    placeholder = "Password",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    isPassword = true
                )
                VerticalSpace(16)
                CustomTextField(
                    value = passwordConfirmation,
                    onValueChange = {
                        passwordConfirmation = it
                    },
                    placeholder = "Password Confirmation",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    isPassword = true
                )
                VerticalSpace(24)
                MubiButton(text = "Register", buttonStyle = MubiButtonStyle(width = 1F)) {
                    registerViewModel.userInput(
                        RegisterAction.RegisterUser(
                            email,
                            emailConfirmation,
                            password,
                            passwordConfirmation
                        )
                    )
                }
                state.value.error?.let { error ->
                    VerticalSpace(8)
                    Text(text = error, color = MaterialTheme.colors.error)
                }
            }
            if (state.value.isLoading)
                Loader()
            if (state.value.isRegisterSuccess == true)
                navController?.navigate(HOME_ROUTE_STRING)

        }

    }
}
package com.applaudostudios.mubi.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.mvi.action.ProfileAction
import com.applaudostudios.mubi.mvi.state.LogOutState
import com.applaudostudios.mubi.ui.component.MubiButton
import com.applaudostudios.mubi.ui.component.MubiButtonStyle
import com.applaudostudios.mubi.ui.component.MubiButtonType
import com.applaudostudios.mubi.ui.navigation.SIGN_IN_ROUTE_STRING
import com.applaudostudios.mubi.ui.theme.MubiPalette

@Composable
fun ProfileScreen(
    navController: NavHostController? = null,
    profileViewModel: ProfileViewModel = viewModel()
) {

    val state = profileViewModel.state.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Profile") }, navigationIcon = {
            IconButton(onClick = {
                navController?.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        })
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "My Favourites", textAlign = TextAlign.Center)
                MubiButton(text = "Log Out", buttonStyle = MubiButtonStyle(width = 1F)) {
                    profileViewModel.userInput(ProfileAction.LogOut)
                }
            }
            if (state.value.logOutState is LogOutState.LogOutRequested) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MubiPalette.DarkColorPalette.background.copy(alpha = 0.7F)),
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8F)
                            .aspectRatio(2F)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Are you sure you want to leave?")
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                MubiButton(
                                    text = "Stay",
                                    buttonStyle = MubiButtonStyle(buttonType = MubiButtonType.TEXT, width = 0.4F)
                                ) {
                                    profileViewModel.userInput(ProfileAction.LogOutCancel)
                                }
                                MubiButton(
                                    text = "Leave",
                                    buttonStyle = MubiButtonStyle(buttonType = MubiButtonType.TEXT, width = 0.4F)
                                ) {
                                    profileViewModel.userInput(ProfileAction.LogOutConfirmation)
                                    //navController?.popBackStack(route = SIGN_IN_ROUTE_STRING, inclusive = true)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
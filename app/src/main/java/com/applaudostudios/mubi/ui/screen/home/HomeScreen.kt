package com.applaudostudios.mubi.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController? = null, homeViewModel:HomeViewModel = viewModel()) {
    Scaffold(
        topBar = {},
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {

        }
    }

}
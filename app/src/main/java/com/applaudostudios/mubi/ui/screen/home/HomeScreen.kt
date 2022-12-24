package com.applaudostudios.mubi.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.applaudostudios.core.domain.model.TVListType

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    homeViewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        topBar = {},
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            Column() {
                Row(){
                    for(item in TVListType.values()){
                        Text(text = item.title())
                    }
                }
            }
        }
    }

}
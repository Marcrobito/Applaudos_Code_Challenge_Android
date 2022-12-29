package com.applaudostudios.mubi.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.mvi.action.HomeAction
import com.applaudostudios.mubi.ui.component.MubiCardComponent
import com.applaudostudios.mubi.ui.component.NavigationListComponent
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.DETAIL_ROUTE_STRING

@Composable
fun HomeScreen(
    navController: NavHostController? = null, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    Scaffold(
        topBar = {},
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                NavigationListComponent(items = TVListType.values().map { it.title() }.toList(),
                    onClick = {
                        homeViewModel.userInput(HomeAction.NavigateToList(TVListType.values()[it]))
                    })
                VerticalSpace(8)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.data) { card ->
                        MubiCardComponent(card = card, onClick = {
                            navController?.navigate("$DETAIL_ROUTE_STRING/${card.id}")
                        })
                    }
                }
            }
            if (state.isLoading)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0.6f, 0.6f, 0.6f, 0.6f)),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
        }
    }

}
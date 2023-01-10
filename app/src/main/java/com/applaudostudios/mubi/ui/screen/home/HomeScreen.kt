package com.applaudostudios.mubi.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.R
import com.applaudostudios.mubi.mvi.action.HomeAction
import com.applaudostudios.mubi.ui.component.MubiCardComponent
import com.applaudostudios.mubi.ui.component.NavigationListComponent
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.navigation.DETAIL_ROUTE_STRING
import com.applaudostudios.mubi.ui.navigation.PROFILE_ROUTE_STRING
import com.applaudostudios.mubi.ui.navigation.SEARCH_ROUTE_STRING

@Composable
fun HomeScreen(
    navController: NavHostController? = null, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    val gridState = rememberLazyGridState()
    var currentType by remember {
        mutableStateOf(state.tvType)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.tv_shows)) },
                actions = {
                    IconButton(onClick = {
                        navController?.navigate(SEARCH_ROUTE_STRING)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        navController?.navigate(PROFILE_ROUTE_STRING)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                NavigationListComponent(items = TVListType.values().map { it.title() }.toList(),
                    onClick = {
                        currentType = TVListType.values()[it]
                        homeViewModel.userInput(HomeAction.NavigateToList(currentType))
                    })
                VerticalSpace(8)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    state = gridState
                ) {
                    items(state.data) { card ->
                        val lastIndex = state.data.lastIndex
                        val currentIndex = state.data.indexOf(card)
                        MubiCardComponent(card = card, onClick = {
                            navController?.navigate("$DETAIL_ROUTE_STRING/${card.id}")
                        })
                        if (lastIndex == currentIndex) {
                            homeViewModel.userInput(HomeAction.NavigateToList(currentType))
                        }
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
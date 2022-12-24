package com.applaudostudios.mubi.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.mvi.action.HomeAction

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {},
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(TVListType.values()) { _, item ->
                        Button(onClick = {
                            homeViewModel.userInput(HomeAction.NavigateToList(item))
                        }) {
                            Text(text = item.title())
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Log.d("HomeScreen", homeViewModel.state.value.toString())
                    homeViewModel.state.value.data.let { cards ->
                        items(cards) { card ->
                            Card(
                                modifier = Modifier.fillMaxHeight(0.3F),
                            ) {
                                Column {
                                    Text(text = card.name)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}
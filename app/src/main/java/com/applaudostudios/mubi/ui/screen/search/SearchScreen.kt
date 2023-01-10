package com.applaudostudios.mubi.ui.screen.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.base.IMAGE_BASE_PATH
import com.applaudostudios.mubi.mvi.action.SearchAction
import com.applaudostudios.mubi.ui.component.CustomTextField
import com.applaudostudios.mubi.ui.component.Loader
import com.applaudostudios.mubi.ui.component.RatingBar
import com.applaudostudios.mubi.ui.navigation.DETAIL_ROUTE_STRING
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController? = null,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    var search by remember {
        mutableStateOf("")
    }
    val state = searchViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
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
                },
                title = {
                    CustomTextField(
                        Modifier.height(40.dp),
                        value = search,
                        onValueChange = {
                            search = it.trim()
                        },
                        placeholder = "Search",
                        overInputPlaceholder = false,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        if (search.isNotBlank()) searchViewModel.userInput(SearchAction(search))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
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
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)) {
                items(state.value.data) { item ->
                    val lastIndex = state.value.data.lastIndex
                    val currentIndex = state.value.data.indexOf(item)
                    
                    Card(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Box() {
                            Row() {
                                GlideImage(
                                    model = IMAGE_BASE_PATH + item.urlImg,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(1F),
                                    contentScale = ContentScale.Crop
                                )
                                Column( modifier = Modifier.padding(8.dp)) {
                                    Text(text = item.name)
                                    RatingBar(rating = item.rating)
                                }
                            }
                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                                modifier = Modifier.fillMaxSize(),
                                elevation = ButtonDefaults.elevation(0.dp),
                                onClick = { navController?.navigate("$DETAIL_ROUTE_STRING/${item.id}") }) {
                            }
                        }

                    }
                    if (lastIndex == currentIndex) {
                        searchViewModel.userInput(SearchAction(search))
                    }
                }
            }
            if (state.value.isLoading)
                Loader()
        }
    }
}
package com.applaudostudios.mubi.ui.screen.season

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.base.IMAGE_BASE_PATH
import com.applaudostudios.mubi.mvi.action.SeasonAction
import com.applaudostudios.mubi.ui.component.MubiText
import com.applaudostudios.mubi.ui.component.MubiTextStyle
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.theme.Gray
import com.applaudostudios.mubi.ui.theme.Purple
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SeasonScreen(
    navController: NavHostController? = null,
    seriesId: Int,
    seasonNumber: Int,
    seasonViewModel: SeasonViewModel = hiltViewModel()
) {
    val state = seasonViewModel.state.collectAsState()

    if (state.value.data == null && state.value.error == null)
        seasonViewModel.userInput(SeasonAction(seasonNumber, seriesId))

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
                    state.value.data?.let {
                        Text(text = it.name)
                    }
                }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            state.value.data?.episodes?.let { episodes ->
                LazyColumn() {
                    items(episodes) { episode ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .height(160.dp)
                                .padding(bottom = 16.dp),
                            elevation = 2.dp
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Row() {
                                    GlideImage(
                                        model = IMAGE_BASE_PATH + (state.value.data!!.image ?: ""),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.33F),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        MubiText(
                                            text = "Episode ${episode.episodeNumber}",
                                            MubiTextStyle(
                                                Color.Black,
                                                textAlign = TextAlign.Justify,
                                                fontSize = 24
                                            )
                                        )
                                        VerticalSpace(8)
                                        MubiText(
                                            text = episode.name,
                                            MubiTextStyle(
                                                Gray,
                                                textAlign = TextAlign.Justify,
                                                fontSize = 18
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}
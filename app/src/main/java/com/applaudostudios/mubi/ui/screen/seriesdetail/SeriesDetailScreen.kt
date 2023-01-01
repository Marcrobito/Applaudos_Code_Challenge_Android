package com.applaudostudios.mubi.ui.screen.seriesdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.applaudostudios.mubi.base.IMAGE_BASE_PATH
import com.applaudostudios.mubi.mvi.action.SeriesDetailAction.ShowSeriesDetail
import com.applaudostudios.mubi.ui.component.MubiText
import com.applaudostudios.mubi.ui.component.MubiTextStyle
import com.applaudostudios.mubi.ui.component.RatingBar
import com.applaudostudios.mubi.ui.component.VerticalSpace
import com.applaudostudios.mubi.ui.theme.Gray
import com.applaudostudios.mubi.ui.theme.MubiPalette
import com.applaudostudios.mubi.ui.theme.Purple
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SeriesDetailScreen(
    navController: NavHostController? = null,
    seriesId: Int,
    seriesDetailViewModel: SeriesDetailViewModel = hiltViewModel()
) {
    seriesDetailViewModel.userInput(ShowSeriesDetail(seriesId))
    val state = seriesDetailViewModel.state.collectAsState()
    Scaffold(
        topBar = {},
    ) { contentPadding ->
        state.value.data?.let { show ->
            Column(
                modifier = Modifier.padding(contentPadding)
            ) {
                Box() {
                    GlideImage(
                        model = IMAGE_BASE_PATH + show.urlImg,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight(0.28F)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.28F)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0F, 0F, 0F, 0.8F))
                                )
                            )
                    )
                    IconButton(onClick = {
                        navController?.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = MubiPalette.DarkColorPalette.background)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.28F)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        MubiText(
                            text = show.tagline,
                            MubiTextStyle(Purple, textAlign = TextAlign.Start)
                        )
                        MubiText(
                            text = show.name,
                            MubiTextStyle(
                                Color.White,
                                textAlign = TextAlign.Justify,
                                fontSize = 32,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        VerticalSpace(8)
                        RatingBar(rating = show.rating, showRating = false, height = 20)
                    }
                }
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    MubiText(
                        text = "Summary",
                        MubiTextStyle(Purple, textAlign = TextAlign.Start, fontSize = 24)
                    )
                    MubiText(
                        text = show.summary,
                        MubiTextStyle(Gray, textAlign = TextAlign.Justify)
                    )
                    VerticalSpace(16)
                    LazyColumn() {
                        items(show.seasons) { season ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .height(180.dp)
                                    .padding(bottom = 16.dp),
                                elevation = 2.dp
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Row() {
                                        GlideImage(
                                            model = IMAGE_BASE_PATH + (season.urlImage
                                                ?: show.urlImg),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .fillMaxWidth(0.33F),
                                            contentScale = ContentScale.Crop
                                        )
                                        Column(modifier = Modifier.padding(8.dp)) {
                                            MubiText(
                                                text = season.name,
                                                MubiTextStyle(
                                                    Gray,
                                                    textAlign = TextAlign.Justify,
                                                    fontSize = 24
                                                )
                                            )
                                            VerticalSpace(8)
                                            MubiText(
                                                text = "Episodes ${season.episodes}",
                                                MubiTextStyle(
                                                    Purple,
                                                    textAlign = TextAlign.Justify,
                                                    fontSize = 12
                                                )
                                            )
                                            VerticalSpace(8)
                                            MubiText(
                                                text = season.summary,
                                                MubiTextStyle(Gray, textAlign = TextAlign.Justify)
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
}

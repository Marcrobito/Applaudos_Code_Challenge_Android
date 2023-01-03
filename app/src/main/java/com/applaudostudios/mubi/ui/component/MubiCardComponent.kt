package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.mubi.base.IMAGE_BASE_PATH
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MubiCardComponent(card: Card, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.aspectRatio(0.75F),
        elevation = 2.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GlideImage(
                    model = IMAGE_BASE_PATH + card.urlImg,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight(0.55F)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                MubiText(
                    text = card.name,
                    textStyle = MubiTextStyle(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        textColor = Color.Gray
                    )
                )
                RatingBar(modifier= Modifier.padding(horizontal = 8.dp),rating = card.rating)
                VerticalSpace(24)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                modifier = Modifier.fillMaxSize(),
                elevation = ButtonDefaults.elevation(0.dp),
                onClick = { onClick() }) {
            }
        }
    }
}
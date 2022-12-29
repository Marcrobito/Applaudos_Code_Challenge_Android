package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.R
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Unspecified,
    showRating: Boolean = true,
    height: Int = 16
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(filledStars) {
            Icon(
                modifier = Modifier.height(height.dp).aspectRatio(1F),
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                modifier = Modifier.height(height.dp).aspectRatio(1F),
                painter = painterResource(id = R.drawable.half_star),
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                modifier = Modifier.height(height.dp).aspectRatio(1F),
                painter = painterResource(id = R.drawable.unfilled_star),
                contentDescription = null,
                tint = starsColor
            )
        }

        if (showRating)
            MubiText(rating.toString(), MubiTextStyle(textColor = Color.Black))
    }
}
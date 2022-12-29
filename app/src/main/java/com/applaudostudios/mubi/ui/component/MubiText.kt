package com.applaudostudios.mubi.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun MubiText(
    text: String,
    textStyle: MubiTextStyle = MubiTextStyle()
) {
    with(textStyle) {
        Text(
            text = text,
            color = textColor,
            modifier = modifier,
            textAlign = textAlign,
            fontSize = fontSize.sp,
            overflow = TextOverflow.Ellipsis
        )
    }

}

data class MubiTextStyle(
    val textColor: Color = Color.White,
    val modifier: Modifier = Modifier,
    val textAlign: TextAlign = TextAlign.Center,
    val fontSize: Int = 16
)
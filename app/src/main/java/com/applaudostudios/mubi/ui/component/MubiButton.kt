package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.ui.theme.PrimaryPurple

@Composable
fun MubiButton(
    modifier: Modifier = Modifier
        .fillMaxWidth(0.9F)
        .height(48.dp),
    text: String = "",
    color: Color = PrimaryPurple,
    textColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = modifier,
        onClick = { onClick() }) {
        Text(text = text, color = textColor)
    }
}


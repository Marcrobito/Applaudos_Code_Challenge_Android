package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.ui.theme.PrimaryPurple

@Composable
fun MubiButton(
    text: String = "",
    buttonStyle: MubiButtonStyle = MubiButtonStyle(),
    enabled: Boolean = true,
    textStyle: MubiTextStyle = MubiTextStyle(),
    onClick: () -> Unit = {}
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonStyle.color,
            disabledBackgroundColor = buttonStyle.disabledColor
        ),
        enabled = enabled,
        modifier = buttonStyle.modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(buttonStyle.corners.dp)
    ) {
        MubiText(text = text, textStyle = textStyle)
    }
}

data class MubiButtonStyle(
    val color: Color = PrimaryPurple,
    val disabledColor: Color = PrimaryPurple,
    val height: Int = 48,
    val width: Float = 0.9F,
    val corners: Int = 12
) {
    val modifier: Modifier = Modifier
        .fillMaxWidth(width)
        .height(height.dp)
}

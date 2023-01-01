package com.applaudostudios.mubi.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.applaudostudios.mubi.Greeting
import com.applaudostudios.mubi.ui.theme.MubiPalette
import com.applaudostudios.mubi.ui.theme.MubiTheme
import com.applaudostudios.mubi.ui.theme.MubiTypography
import com.applaudostudios.mubi.ui.theme.Purple

@Composable
fun MubiButton(
    text: String = "",
    buttonStyle: MubiButtonStyle = MubiButtonStyle(),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = when (buttonStyle.buttonType) {
    MubiButtonType.REGULAR -> MubiRegularButton(
        text = text,
        buttonStyle = buttonStyle,
        enabled = enabled,
        onClick = onClick
    )
    MubiButtonType.TEXT -> MubiTextButton(
        text = text,
        buttonStyle = buttonStyle,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
private fun MubiRegularButton(
    text: String = "",
    buttonStyle: MubiButtonStyle = MubiButtonStyle(),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = with(buttonStyle) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = disabledColor
        ),
        enabled = enabled,
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(corners.dp)
    ) {
        Text(text = text.uppercase(), style = textStyle, color = textColor)
    }
}

@Composable
private fun MubiTextButton(
    text: String = "",
    buttonStyle: MubiButtonStyle = MubiButtonStyle(),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = with(buttonStyle) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() },
        enabled = enabled
    ) {
        Text(text = text, style = textStyle, color = textColor)
    }
}

enum class MubiButtonType {
    REGULAR, TEXT
}

data class MubiButtonStyle(
    val buttonType: MubiButtonType = MubiButtonType.REGULAR,
    val color: Color = Purple,
    val disabledColor: Color = Purple,
    val height: Int = 48,
    val width: Float = 0.9F,
    val corners: Int = 12,
) {
    var textColor: Color = when(buttonType){
        MubiButtonType.REGULAR -> MubiPalette.Button
        MubiButtonType.TEXT -> MubiPalette.Text
    }
    var textStyle: TextStyle = when(buttonType){
        MubiButtonType.REGULAR -> MubiTypography.button
        MubiButtonType.TEXT -> MubiTypography.body1
    }
    val modifier: Modifier = Modifier
        .fillMaxWidth(width)
        .height(height.dp)
}

@Preview(showBackground = true)
@Composable
fun MubiRegularButtonPreview() {
    MubiTheme {
        MubiRegularButton("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MubiTextButtonPreview() {
    MubiTheme {
        MubiTextButton("Android")
    }
}

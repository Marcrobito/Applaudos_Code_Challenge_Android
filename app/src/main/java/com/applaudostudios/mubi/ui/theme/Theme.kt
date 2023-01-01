package com.applaudostudios.mubi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun MubiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        MubiPalette.DarkColorPalette
    } else {
        MubiPalette.LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MubiTypography,
        shapes = Shapes,
        content = content
    )
}
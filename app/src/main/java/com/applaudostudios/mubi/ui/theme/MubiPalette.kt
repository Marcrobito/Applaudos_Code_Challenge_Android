package com.applaudostudios.mubi.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object MubiPalette {
    //Primary
    private val MajorelleBlue = Color(0xFF6243FF) //Purple
    private val MediumBlue = Color(0xFF0013CA) //Blue

    //Tertiary
    private val MaximumBlueGreen = Color(0xFF21BDCA) //Turquoise
    private val SkyBlueCrayola = Color(0xFF84DFE2) //TurquoiseLight
    private val BlueJeans = Color(0xFF32B4FF) //LightBlue

    //Neutrals
    private val SpaceCadet = Color(0xFF25294A) //Black
    private val Cultured = Color(0XFFF0F2F5) //Background
    private val GhostWhite = Color(0XFFFBFAFE) //White
    private val Rhythm = Color(0xFF6B6B83) //Text
    private val CoolGray = Color(0XFF8C8CA1) //Subtle Text
    private val Gainsboro = Color(0XFFD5D8DB) //Gray


    val Button = GhostWhite
    val Text = Rhythm
    val Link = MaximumBlueGreen
    val Background = Cultured
    val SubtleText = CoolGray
    val Gray = Gainsboro

    //Error
    private val FieryRose = Color(0XFFF65164) //Red

    val DarkColorPalette = darkColors(
        primary = MajorelleBlue,
        primaryVariant = Purple700,
        secondary = MediumBlue,
        background = SpaceCadet,
        onError = FieryRose
    )

    val LightColorPalette = lightColors(
        primary = MajorelleBlue,
        primaryVariant = Purple700,
        secondary = MediumBlue,
        background = Cultured,
        onError = FieryRose,
        error = FieryRose

        /* Other default colors to override
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
        */
    )
}
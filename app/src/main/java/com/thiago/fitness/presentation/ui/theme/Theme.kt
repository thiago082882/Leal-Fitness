package com.thiago.fitness.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue500,
    secondary = White,
    onSecondary = DarkGray500,
    background = DarkGray900,
    onPrimary = White,
)

private val LightColorPalette = lightColors(
    primary = Blue200,
    primaryVariant = Blue500,
    secondary = DarkGray700

)

@Composable
fun AllFitnessTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}

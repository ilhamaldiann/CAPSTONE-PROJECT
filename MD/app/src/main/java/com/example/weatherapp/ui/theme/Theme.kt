package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkmode_primary_bg,
    primaryVariant = Purple700,
    secondary = darkmode_secondary_bg,
    onPrimary = darkmode_primary_text,
    onSecondary = darkmode_secondary_text
)

private val LightColorPalette = lightColors(
    primary = lightmode_primary_bg,
    primaryVariant = Purple700,
    secondary = lightmode_secondary_bg,
    onPrimary = lightmode_primary_text,
    onSecondary = lightmode_secondary_text
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
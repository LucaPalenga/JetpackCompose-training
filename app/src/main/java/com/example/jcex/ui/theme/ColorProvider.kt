package com.example.jcex.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

data class ColorProvider(val colorScheme: ColorScheme) {
    val darkColors = darkColors(primary = colorScheme.primaryDark)
    val lightColors = lightColors(primary = colorScheme.primaryLight)
}

data class ColorScheme(val primaryLight: Color, val primaryDark: Color)
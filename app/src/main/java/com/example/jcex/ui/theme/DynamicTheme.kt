package com.example.jcex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.google.android.material.composethemeadapter3.Mdc3Theme

@Composable
fun DynamicTheme(
    colorProvider: ColorProvider,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    Mdc3Theme(content = content)
}
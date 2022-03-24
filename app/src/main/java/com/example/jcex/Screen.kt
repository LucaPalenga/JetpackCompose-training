package com.example.jcex

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val icon: ImageVector) {
    object MainScreen : Screen("main_screen", Icons.Default.Home)
    object ComponentsScreen : Screen("components", Icons.Filled.RadioButtonUnchecked)
    object KotlinTrainingScreen : Screen("kotlin", Icons.Filled.ReadMore)
    object StateScreen : Screen("state", Icons.Filled.QueryStats)
    object Animations : Screen("animations", Icons.Filled.Animation)
    object Layouts : Screen("layouts", Icons.Filled.Layers)
    object Theming : Screen("theming", Icons.Filled.Style)
    object ViewToComposeScreen : Screen("view_to_compose", Icons.Filled.ViewModule)
}

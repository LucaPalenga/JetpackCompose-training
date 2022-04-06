package com.example.jcex.kotlintraining.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jcex.kotlintraining.ClassScreeen
import com.example.jcex.kotlintraining.CollectionsScreen
import com.example.jcex.kotlintraining.ExtensionsScreen
import com.example.jcex.kotlintraining.GenericsScreen

enum class TabPage(val icon: ImageVector, val screen: @Composable () -> Unit) {
    Collections(Icons.Default.Collections, { CollectionsScreen() }),
    Classes(Icons.Default.Class, { ClassScreeen() }),
    Functions(Icons.Default.Functions, { com.example.jcex.kotlintraining.FunctionsScreen() }),
    Extensions(Icons.Default.Extension, { ExtensionsScreen() }),
    Generics(Icons.Default.Code, { GenericsScreen() })
}

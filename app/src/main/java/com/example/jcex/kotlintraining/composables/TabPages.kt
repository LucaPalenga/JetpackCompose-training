package com.example.jcex.kotlintraining.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jcex.kotlintraining.*

enum class TabPage(val icon: ImageVector, val screen: @Composable () -> Unit) {
    Collections(Icons.Default.Collections, { CollectionsScreen() }),
    Classes(Icons.Default.Class, { ClassScreeen() }),
    Functions(Icons.Default.Functions, { FunctionsScreen() }),
    Extensions(Icons.Default.Extension, { ExtensionsScreen() }),
    Generics(Icons.Default.Code, { GenericsScreen() }),
    FunctionalManipulation(Icons.Default.Keyboard, { FunctionalManipulationScreen() })
}

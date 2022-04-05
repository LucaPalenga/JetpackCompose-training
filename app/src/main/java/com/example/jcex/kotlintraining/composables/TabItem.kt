package com.example.jcex.kotlintraining.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Collections
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jcex.kotlintraining.ClassScreeen
import com.example.jcex.kotlintraining.Collections

enum class TabPage(val icon: ImageVector, val screen: @Composable () -> Unit) {
    Collections(Icons.Default.Collections, { Collections() }),
    Classes(Icons.Default.Class, { ClassScreeen() }),
//    Extensions(Icons.Default.Extension, { ExtensionsScreen() })
}

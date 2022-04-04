package com.example.jcex.kotlintraining

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable


@Composable
fun KotlinTrainingScreen() {
    LazyColumn {
        item { Lists() }
        item { InterfaceDelegation() }
        item { DataClass() }
    }
}
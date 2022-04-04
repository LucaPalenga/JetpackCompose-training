package com.example.jcex.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jcex.layouts.*
import com.example.jcex.ui.theme.Shapes

@Composable
fun ComposableScreen() {
    val navController = rememberNavController()
    NavigationHost(navController)
}

@Composable
fun ComponentsList(navController: NavHostController, components: List<String>) {
    Column {
        TopAppBar {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Components examples",
                fontSize = 22.sp
            )
        }
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(components) {
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    ComponentCard(it, onItemClick = {
                        navController.navigate(it)
                    })
                }
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    val componentsExamples = listOf(
        "Buttons",
        "Chip",
        "ImageCard",
        "Lists",
        "Scaffold",
        "HalfImageCard",
        "CircularStatsIndicator"
    )

    NavHost(navController = navController, startDestination = "ComposableScreen") {
        composable("ComposableScreen") {
            ComponentsList(navController, componentsExamples)
        }
        composable("Buttons") {
            ComposeButton()
        }
        composable("Chip") {
            Chip(text = "Ciao")
        }
        composable("ImageCard") {
            ImageCard()
        }
        composable("Lists") {
            SimpleList()
        }
        composable("Scaffold") {
            LayoutLab()
        }
        composable("HalfImageCard") {
            HalfImageCard()
        }
        composable("CircularStatsIndicator") {
            CircularStatsIndicatorScreen()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentCard(text: String, onItemClick: (String) -> Unit) {
    Card(shape = Shapes.medium, onClick = { onItemClick(text) }) {
        Text(modifier = Modifier.padding(8.dp), text = text)
    }
}

@Preview
@Composable
fun ComposableScreenPreview() {
    ComposableScreen()
}
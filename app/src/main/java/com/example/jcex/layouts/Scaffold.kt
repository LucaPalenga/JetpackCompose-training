package com.example.jcex.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun LayoutLabPreview() {
    LayoutLab()
}

@Composable
fun LayoutLab() {
    /**
     * Scaffold è una struttura base per un layout Material Design
     * è predisposto per contenere al suo interno Top/BottomAppBar, FABs e Drawer
     */
    Scaffold(topBar = { TopBarContent() })
    { innerPadding ->
        BodyContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
    }
}

@Composable
fun TopBarContent() {
    TopAppBar(title = {
        Text(text = "Top bar title")//, style = MaterialTheme.typography.h3)
    },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        })
}

@Composable
fun BodyContent(modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "Hello")
        Text(text = "Hola")
    }
}
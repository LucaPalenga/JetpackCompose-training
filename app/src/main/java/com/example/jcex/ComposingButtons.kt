package com.example.jcex

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun ComposeButton() {
    Button(
        onClick = { /*TODO*/ }) {
        HeartIcon()
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Button")
    }
}

@Preview
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Top bar", maxLines = 2) },
        navigationIcon = { HeartIcon() }
    )
}

@Composable
fun HeartIcon() {
    Icon(
        painter = painterResource(id = R.drawable.heart),
        modifier = Modifier.size(16.dp),
        contentDescription = "cuore"
    )
}
package com.example.jcex.kotlintraining

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcex.ui.theme.Shapes


/**
 * Lista immutabile
 */
val immutableList = listOf("pippo", "pluto", "paperino")

/**
 * Lista mutabile
 */
val mutableList = mutableListOf(1, 2, 3, 4)


@Composable
fun Lists() {
    Column() {
        Surface(
            Modifier.fillMaxWidth().padding(8.dp),
            shape = Shapes.medium,
            elevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                TitleList("Lista immutabile - listOf")
                ListContainer(modifier = Modifier.padding(top = 8.dp), list = immutableList)
            }
        }
        Surface(
            Modifier.fillMaxWidth().padding(8.dp),
            shape = Shapes.medium,
            elevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                TitleList("Lista mutabile - mutableListOf")
                Subtitle(mutableList.remove(1).toString())
                ListContainer(mutableList)
            }
        }
    }
}

@Composable
fun TitleList(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        text = text
    )
}

@Composable
fun Subtitle(text: String) {
    Text(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        text = "Remove element 1 = $text"
    )
}

@Composable
fun ListContainer(list: List<Any>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)) {
        items(list) { item ->
            Text(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), text = item.toString())
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    Lists()
}
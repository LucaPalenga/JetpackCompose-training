package com.example.jcex.kotlintraining

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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

/**
 * Arrays
 */
val array1 = arrayOf("pippo", 1, 2.0)
val array2 = intArrayOf(2, 3)

@Composable
fun Lists() {
    Column() {
        CardContainer {
            TitleList("Lista immutabile - listOf")
            ListContainer(list = immutableList)
        }
        CardContainer {
            TitleList("Lista mutabile - mutableListOf")
            Subtitle("Remove element 1 = ${mutableList.remove(1)}")
            ListContainer(mutableList)
        }
        CardContainer {
            TitleList("Array (sempre immutabile) - arrayOf")
            ArrayContainer(array1 as Array<Any>)
        }

    }
}

@Composable
fun CardContainer(content: @Composable () -> Unit) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = Shapes.medium,
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            content()
        }
    }
}

@Composable
fun TitleList(text: String, modifier: Modifier = Modifier.padding(vertical = 8.dp)) {
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
        text = text
    )
}

@Composable
fun ListContainer(list: List<Any>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)) {
        items(list) { item ->
            Text(modifier = Modifier.padding(4.dp), text = item.toString())
        }
    }
}

@Composable
fun ArrayContainer(array: Array<Any>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)) {
        items(array) { item ->
            Text(modifier = Modifier.padding(4.dp), text = item.toString())
        }
    }
}

@Preview
@Composable
fun ListPreview() {
    Lists()
}
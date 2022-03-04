package com.example.jcex.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch


@Composable
fun ListsPreview() {
    SimpleList()
}

@Composable
fun SimpleList() {
    /**
     * Ha bisogno di un modifier con vertical scroll per abilitare lo scroll
     * poiché la column non lo gestisce di default
     */
    Column(Modifier.verticalScroll(rememberScrollState())) {
        repeat(100)
        {
            Text("Item #$it")
        }
    }
}

@Composable
fun LazyList() {
    /**
     * La LazyColumn invece gestisce lo scroll di default
     */
    LazyColumn() {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Preview
@Composable
fun LazyScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    /**
     * Per impedire che si blocchi il render della lista ho bisogno di una coroutine
     * per animare lo scroll automatico
     */

    Column {
        Row() {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Torna all'inizio")
                Icon(Icons.Filled.KeyboardArrowUp, null)
            }
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize)
                }
            }) {
                Text(text = "Vai alla fine")
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
            }
        }
        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(itemNumber = it)
            }
        }
    }

}

@Composable
fun ImageListItem(itemNumber: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ), contentDescription = "Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Element #$itemNumber")
    }
}
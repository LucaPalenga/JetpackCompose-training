package com.example.jcex.kotlintraining

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcex.ui.theme.Shapes


// region containers

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
fun ListContainer(list: List<Any>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(vertical = 4.dp)) {
        items(list) { item ->
            Text(modifier = Modifier.padding(end = 4.dp), text = item.toString())
        }
    }
}

@Composable
fun ArrayContainer(array: Array<Any>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)) {
        items(array) { item ->
            Text(modifier = Modifier.padding(end = 4.dp), text = item.toString())
        }
    }
}

// endregion containers

// region texts

@Composable
fun ScreenTitle(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(vertical = 8.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = color
    )
}

@Composable
fun CardTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(bottom = 8.dp),
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        text = text
    )
}

@Composable
fun CardSubtitle(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        text = text
    )
}

@Composable
fun CardSubtitleOSS(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        text = text,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun TextSemibold(text: String, modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = text, fontWeight = FontWeight.SemiBold)
}

@Composable
fun TextBold(text: String, modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = text, fontWeight = FontWeight.Bold)
}

// endregion texts

// region spacers

@Composable
fun SmallSpacer() {
    Spacer(Modifier.size(4.dp))
}

@Composable
fun MediumSpacer() {
    Spacer(Modifier.size(8.dp))
}

@Composable
fun NormalSpacer() {
    Spacer(Modifier.size(16.dp))
}

@Composable
fun BigSpacer() {
    Spacer(Modifier.size(24.dp))
}

// endregion spacers


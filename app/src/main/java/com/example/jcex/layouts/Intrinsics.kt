package com.example.jcex.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Ricordando che misurare 2 volte un child lancia una runtime exception
 * un intrinsic permette di richiedere ad un composable le sue dimensioni
 * anche dopo che è stato misurato (cioè dopo l'esecuzione del .measure)
 * intrinsicWidth
 * intrinsicHeight
 */

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    // InstrinsicSize.Min ricorsivamente andrà a vedere qual'è il valore massimo
    // tra i minIntrinsicHeight dei figli di Row
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = text1,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )
//        Button(
//            onClick = {}, modifier = Modifier
//                .weight(1f)
//                .wrapContentWidth(Alignment.Start)
//        ) {
//            Text(text = "Ciao")
//        }
        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            text = text2,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    TwoTexts(text1 = "Hi", text2 = "there")
}

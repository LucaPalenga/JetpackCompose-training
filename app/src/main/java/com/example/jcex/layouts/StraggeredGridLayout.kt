package com.example.jcex.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.SampleData.topics
import com.example.jcex.ui.theme.JCExTheme

/**
 *  Creazione di un layout custom tramite la classe Layout
 */

@Composable
fun StraggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {

    Layout(modifier = modifier, content = content) { measurables, constraints ->
        // salvo larghezza e altezza di ogni row
        val rowWidth = IntArray(rows) { 0 }
        val rowHeight = IntArray(rows) { 0 }

        // mapIndexed: applica la trasformazione ad ogni elemento con relativo indice (measurable) del layout
        val placeables = measurables.mapIndexed { index, measurable ->
            // measure ogni child da cui ricava un placeable cioè un child del layout che può essere riposizionato
            val placeable = measurable.measure(constraints = constraints)

            // ricavo la riga in base alla mia regola, in questo caso l'indice della riga corrente va in base alla scelta del numero di righe
            val currentRow = index % rows
            // salva width e max height di ogni riga
            rowWidth[currentRow] += placeable.width
            rowHeight[currentRow] = Math.max(rowHeight[currentRow], placeable.height)

            placeable
        }

        // calcolo larghezza e altezza massima che avrà l'intero layout
        val width =
            rowWidth.maxOrNull()?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))
                ?: constraints.minWidth
        val height =
            rowHeight.sumOf { it }.coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // tengo traccia della coordinata y di ogni riga
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeight[i - 1]
        }

        // determino le dimensioni del layout e posiziono i suoi placeables
        layout(width = width, height = height) {
            // tengo traccia della coordinata x di ogni riga
            val rowX = IntArray(rows) { 0 }

            placeables.forEachIndexed { index, placeable ->
                // ricavo la riga in base alla mia regola
                val row = index % rows
                //posiziono il placeable in base alle coordinate (x,y)
                placeable.placeRelative(x = rowX[row], y = rowY[row])

                //aggiorno la coordinata x aggiungendo la larghezza del placeable appena posizionato
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun StraggeredGridLab() {
    StraggeredGrid(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        rows = 5
    ) {
        for (topic in topics) {
            Chip(modifier = Modifier.padding(4.dp), text = topic)
        }
    }
}

@Composable
fun StraggeredGridWithCustomModifierLab(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(16.dp)
        .size(200.dp)
        .horizontalScroll(rememberScrollState()),
        content = {
            StraggeredGrid() {
                for (topic in topics) {
                    Chip(modifier = Modifier.padding(8.dp), text = topic)
                }
            }
        })
}

@Preview
@Composable
fun StraggeredGridPreview() {
    JCExTheme {
//        StraggeredGridLab()
        StraggeredGridWithCustomModifierLab()
    }
}
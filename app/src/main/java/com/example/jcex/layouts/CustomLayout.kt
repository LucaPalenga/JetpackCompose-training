package com.example.jcex

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jcex.ui.theme.JCExTheme


/**
 * Creazione di un layout custom attraverso il layout modifier
 * NB: gestisce il modifier di un singolo composable
 *
 * in generale:
 * fun Modifier.customLayoutModifier() = Modifier.layout { measurable, constraints -> }
 */

@Composable
fun Modifier.firstBaselineToTop(baselineDp: Dp) =
    //concatena questo modifier con il nuovo layout che sto creando
    this.then(layout { measurable, constraints ->

        /**
         * A Placeable corresponds to a child layout that can be positioned by its parent layout.
         * Most Placeables are the result of a Measurable.measure call.
         */
        val placeable =
            measurable.measure(constraints = constraints) //you can only measure your children once!

        // cerca e setta la prima baseline se c'è
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // togli dalla y dove si trova il composable la baseline trovata
        val placeableY = baselineDp.roundToPx() - firstBaseline
        // ricava l'altezza che dovrà avere il layout in base alla nuova y
        val height = placeableY + placeable.height

        layout(placeable.width, height) {
            //posiziona il composable
            placeable.placeRelative(0, placeableY)
        }

    })

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    JCExTheme {
        Text("Hi there", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalpaddingPreview() {
    JCExTheme {
        Text(text = "Hi there", Modifier.padding(top = 32.dp))
    }
}


/**
 * Creazione di un layout custom aon Layout composable
 * NB: Utile per gestire gruppi di composables
 *
 * in generale:
 * @Composable
 * fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
 * Layout(
 * modifier = modifier,
 * content = content
 * ) { measurables, constraints ->
 * // children measure,constrains and positions
 * }
 * }
 */

@Composable
fun MyOwnColumn(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable -> measurable.measure(constraints) }

        // setta i constraint del layout (quindi la column) "match parent"
        layout(constraints.maxWidth, constraints.maxHeight) {

            var positionY = 0

            //posiziona i children nel layout
            placeables.forEach { placeable ->
                placeable.placeRelative(0, positionY)
                positionY += placeable.height
            }
        }
    }
}

@Composable
fun MyOwnColumnContent(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier = Modifier.padding(8.dp)) {
        Text(text = "MyOwnColumn")
        Text(text = "places items")
        Text(text = "vertically")
        Text(text = "We have done it by hand!")
    }
}

@Composable
fun CustomColumnLayoutLab() {
    MyOwnColumnContent(modifier = Modifier.padding(8.dp))
}





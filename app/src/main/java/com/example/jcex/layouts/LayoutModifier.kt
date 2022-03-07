package com.example.jcex.layouts

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.*

/**
 *  Creazione di un Modifier custom
 *  (Modifier e LayoutModifier sono interfacce pubbliche)
 */

@Stable
fun Modifier.padding(all: Dp) =
    this.then(
        //  Concateno il mio paddingmodifier per gestire il comportamento della funzione padding
        //  tramite la classe PaddingModifier
        PaddingModifier(start = all, top = all, bottom = all, end = all)
    )

/**
 * Faccio override dell'interfaccia LayoutModifier (e quindi della funzione measure) per cambiare
 * il comportamento del modifier
 */
class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val end: Dp = 0.dp
) : LayoutModifier {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        return layout(width, height) {
            placeable.placeRelative(start.roundToPx(), top.roundToPx())
        }
    }
}


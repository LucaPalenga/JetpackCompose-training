package com.example.jcex.layouts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


/**
 * Creazione di un ConstraintLayout
 * pattern:
 * 1. Creazione dei riferimenti per il constraint layout
 *    -> val (ComposableObj...) = createRefs() || val ComposableObj = createRef()
 * 2. Per ogni child nel layout applicare (tramite Modifier) i constraints
 *    -> Modifier.constrainAs(obj) {
 *    latoObj.linkTo(parent/altroObj.lato)
 *    centering
 *    Dimension
 *    ...
 *    }
 */

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {

        val (btn1, btn2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(btn1) { top.linkTo(parent.top, margin = 16.dp) }
        ) {
            Text(text = "Button 1")
        }

        Text(text = "Text",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(btn1.bottom, margin = 16.dp)

                // setta entrambi start e end
                //  centerHorizontallyTo(parent)
                centerAround(btn1.end)
            })

        // barrier
        val barrier = createEndBarrier(btn1, text)
        Button(onClick = { /*TODO*/ },
            Modifier.constrainAs(btn2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }) {
            Text(text = "Button 2")
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(text = "this is a very very very very very very very very very very long text",
            modifier = Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)

                /**
                 * Available Dimension behaviors are:
                 * preferredWrapContent - the layout is wrap content, subject to the constraints in that dimension.
                 * wrapContent - the layout is wrap content even if the constraints would not allow it.
                 * fillToConstraints - the layout will expand to fill the space defined by its constraints in that dimension.
                 * preferredValue - the layout is a fixed dp value, subject to the constraints in that dimension.
                 * value - the layout is a fixed dp value, regardless of the constraints in that dimension
                 */
                width = Dimension.preferredWrapContent
                // la Dimension può essere forzata
                //  width = Dimension.preferredWrapContent.atMost(100.dp)
            })
    }
}


/**
 * Se i constraint su cui voglio operare sono disaccoppiati devo:
 * 1. creare un ConstraintSet contenente i riferimenti e le regole di constraints
 * dei composables interessati e passarlo come parametro al ConstraintLayout che li
 * vuole includere
 * 2. da quest'ultimo ConstraintLayout assegnare i riferimenti creati nel ConstraintSet
 * ai composables tramite layoutId(ref)
 */

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints() {
        //        val constraints = when (LocalConfiguration.current.orientation) {
        //            Configuration.ORIENTATION_LANDSCAPE -> decoupledConstraints(32.dp)
        //            else -> decoupledConstraints(16.dp)
        //        }
        ConstraintLayout(constraintSet = decoupledConstraints(16.dp)) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("button")) {
                Text(text = "Button")
            }
            Text(text = "Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview() {
//    ConstraintLayoutContent()
//    LargeConstraintLayout()
    DecoupledConstraintLayout()
}
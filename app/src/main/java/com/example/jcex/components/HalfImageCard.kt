package com.example.jcex.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcex.R
import com.example.jcex.ui.theme.customStyle


@Composable
fun HalfImageCard() {
    Card(
        Modifier.fillMaxWidth()
            .padding(8.dp)
            .heightIn(min = 200.dp)
            .height(IntrinsicSize.Min), //necessary for DashBorder's Canvas fillMaxSiz
        shape = RoundedCornerShape(8.dp)
    ) {
        DashBorder()
        /**
         *  Why is this so long? We explicitly asked for fillMaxHeight() meaning the max combined height of text composables.
         *  As explained in the intro, compose measures the Row children individually only once, and at the time of drawing
         *  the Image it doesn't know the max available height of the text composables.
         *  This can be fixed by adding height(IntrinsicSize.Min) to the Row parent composable:
         */
        Row {
            /**
             *  This modifier forces the Row children to use their minimum intrinsic size. When measuring is taking place,
             *  now Compose has enough info to calculate fillMaxHeight() for the Image.
             */
            Image(
                painter = painterResource(R.drawable.cairoli),
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(150.dp).fillMaxHeight()
            )
            Column(Modifier.padding(8.dp).weight(1f)) {
                Text("typography custom style", style = MaterialTheme.typography.customStyle)
                Spacer(Modifier.size(8.dp))
                Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
            }
        }
    }
}

@Composable
fun DashBorder() {
//    val stroke = Stroke(
//        width = 1f,
//        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 4f, 0f))
//    )

//    Box(Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRoundRect(
            color = Color.Red, style = Stroke(
                width = 1.dp.toPx(), pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(8.dp.toPx(), 4.dp.toPx())
                )
            ),
            cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx())
        )
    }
//    }
}

@Preview
@Composable
fun HalfImageCardPreview() {
    HalfImageCard()
}

@Preview
@Composable
fun BorderPreview() {
    DashBorder()
}


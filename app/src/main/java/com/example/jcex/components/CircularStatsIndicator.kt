package com.example.jcex.components

import android.content.res.Resources
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcex.R
import com.example.jcex.layouts.padding
import com.example.jcex.ui.theme.colorTotal
import com.example.jcex.ui.theme.colorVal1
import com.example.jcex.ui.theme.colorVal2
import com.example.jcex.ui.theme.statsIndicatorMiniCardColor

/***
 * PAllUA - 31/03/22
 */

@Composable
fun CircularStatsIndicatorScreen() {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            Text(
                text = "StatsIndicator(total = 200f, val1 = 150f, val2 = 50f)",
                fontSize = 12.sp
            )
            StatsIndicator(total = 200f, val1 = 150f, val2 = 50f)
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "StatsIndicatorMini(total = 10f, val1 = 2f, val2 = 8f, animated = false)",
                fontSize = 12.sp
            )
//            StatsIndicatorMini(total = 200f, val1 = 150f, val2 = 50f, animated = false)
            StatsIndicatorMini(total = 10f, val1 = 2f, val2 = 8f, animated = false)
//            StatsIndicatorMini(total = 50f, val1 = 30f, val2 = 20f, animated = false)
//            StatsIndicatorMini(total = 100f, val1 = 50f, val2 = 50f, animated = false)
//            StatsIndicatorMini(total = 200f, val1 = 150f, val2 = 50f, animated = false)
        }
    }
}

val Float.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
val Dp.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density).value

@Composable
fun StatsIndicatorMini(
    modifier: Modifier = Modifier,
    total: Float,
    val1: Float,
    val2: Float,
    totalColor: Color = colorTotal,
    val1Color: Color = colorVal1,
    val2Color: Color = colorVal2,
    animated: Boolean = true,
    durationMillis: Int = 1500,
) {

    //TODO check values concistency
    if (val1 + val2 != total) {
    }

    Card(
        modifier = modifier.wrapContentWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = statsIndicatorMiniCardColor
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularStatsIndicatorComponent(
                total = total,
                rightExternalLineValue = val1,
                leftExternalLineValue = val2,
                rightExternalLineColor = val1Color,
                leftExternalLineColor = val2Color,
                innerLineColor = totalColor,
                animated = animated,
                diameter = 140.dp,
                durationMillis = durationMillis,
                modifier = Modifier.padding(20.dp)
            ) {
                TextStatsIndicatorContent(value = total.toInt(), label = "total")
            }
            StatsColumnMini(
                modifier = Modifier.padding(horizontal = 20.dp),
                total = total, val1 = val1, val2 = val2,
                totalColor = totalColor, val1Color = val1Color, val2Color = val2Color
            )
        }
    }
}

@Composable
fun StatsColumnMini(
    modifier: Modifier = Modifier,
    total: Float,
    val1: Float,
    val2: Float,
    totalColor: Color,
    val1Color: Color,
    val2Color: Color
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
        Text(text = "label total", fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = totalColor, fontSize = 13.sp, value = total.toInt())
        Text(text = "label val2", fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val2Color, fontSize = 13.sp, value = val2.toInt())
        Text(text = "label val1", fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val1Color, fontSize = 13.sp, value = val1.toInt())
    }
}

@Composable
fun StatsIndicator(
    modifier: Modifier = Modifier,
    total: Float,
    val1: Float,
    val2: Float,
    totalColor: Color = colorTotal,
    val1Color: Color = colorVal1,
    val2Color: Color = colorVal2,
    animated: Boolean = true,
    durationMillis: Int = 1500,
) {

    //TODO check values concistency
    if (val1 + val2 != total) {
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularStatsIndicatorComponent(
            total = total,
            rightExternalLineValue = val1,
            leftExternalLineValue = val2,
            rightExternalLineColor = val1Color,
            leftExternalLineColor = val2Color,
            innerLineColor = totalColor,
            animated = animated,
            durationMillis = durationMillis,
            modifier = Modifier.padding(20.dp)
        ) {
            TextStatsIndicatorContent(value = total.toInt(), label = "total")
        }
        StatsColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            total = total, val1 = val1, val2 = val2,
            totalColor = totalColor, val1Color = val1Color, val2Color = val2Color
        )
    }
}

@Composable
fun StatsColumn(
    modifier: Modifier = Modifier,
    total: Float,
    val1: Float,
    val2: Float,
    totalColor: Color,
    val1Color: Color,
    val2Color: Color
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
        Text(text = "label total", maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = totalColor, value = total.toInt())
        Text(text = "label val2", maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val2Color, value = val2.toInt())
        Text(text = "label val1", maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val1Color, value = val1.toInt())
    }
}

@Composable
fun IconWithLabel(color: Color, value: Int, fontSize: TextUnit = 16.sp) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .requiredSize(8.dp)
                .background(color)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(
                id = R.string.stat_value_h,
                formatArgs = arrayOf(value)
            ),
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun TextStatsIndicatorContent(value: Int, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(
                id = R.string.stat_value_h,
                formatArgs = arrayOf(value)
            ), fontWeight = FontWeight.Bold
        )
        Text(text = label)
    }
}

@Composable
fun CircularStatsIndicatorComponent(
    total: Float,
    rightExternalLineValue: Float,
    leftExternalLineValue: Float,
    modifier: Modifier = Modifier,
    rightExternalLineColor: Color,
    leftExternalLineColor: Color,
    innerLineColor: Color,
    rightExternalLineStrokeWidth: Dp = 8.dp,
    paddingBetweenExternalCircles: Dp = 2.dp,
    paddingBetweenInnerCircles: Dp = 8.dp,
    diameter: Dp = 170.dp,
    animated: Boolean = true,
    durationMillis: Int = 1500,
    content: @Composable () -> Unit = {}
) {

    val leftExternalLineStrokeWidth = (rightExternalLineStrokeWidth / 2)
    val innerLineStrokeWidth = (rightExternalLineStrokeWidth / 3)

    val offsetInnerDiameter =
        (rightExternalLineStrokeWidth + innerLineStrokeWidth + paddingBetweenInnerCircles)
    val innerDiameter = diameter - offsetInnerDiameter
    val innerTopLeftOffset = offsetInnerDiameter.px / 2
    val innerCircleTopLeftOffset = Offset(innerTopLeftOffset, innerTopLeftOffset)

    val offsetLeftDiameter = (rightExternalLineStrokeWidth / 2)
    val leftDiameter = diameter - offsetLeftDiameter
    val leftTopLeftOffset = offsetLeftDiameter.px / 2
    val leftCircleTopLeftOffset = Offset(leftTopLeftOffset, leftTopLeftOffset)


    Box(
        modifier
            .padding(rightExternalLineStrokeWidth)
            .size(diameter)
    ) {

        Box(modifier = Modifier.align(Alignment.Center)) {
            content()
        }

        //inner circle
        DrawCircumference(
            diameter = innerDiameter,
            topLeftOffset = innerCircleTopLeftOffset,
            color = innerLineColor,
            stroke = Stroke(width = innerLineStrokeWidth.px, cap = StrokeCap.Round),
            animated = animated,
            durationMillis = durationMillis
            //inner circle variant singleArc = true (default)
        )

//        val percPadding = paddingBetweenExternalCircles.px / total
        val percPadding = paddingBetweenExternalCircles.px / 100

        //external right line
        if (rightExternalLineValue != 0f) {
            val percVal1 = rightExternalLineValue / total
            DrawCircumference(
                diameter = diameter,
                color = rightExternalLineColor,
                stroke = Stroke(width = rightExternalLineStrokeWidth.px, cap = StrokeCap.Round),
                animated = animated,
                durationMillis = durationMillis,
                singleArc = false,
                endValue = if (percVal1 != 1f) {
                    percVal1 - percPadding / 2
                } else {
                    percVal1
                }
            )
        }

        //external left line
        if (leftExternalLineValue != 0f) {
            val percVal2 = leftExternalLineValue / total
            DrawCircumference(
                diameter = leftDiameter,
                topLeftOffset = leftCircleTopLeftOffset,
                color = leftExternalLineColor,
                stroke = Stroke(leftExternalLineStrokeWidth.px, cap = StrokeCap.Round),
                startAngle = -180f,
                animated = animated,
                durationMillis = durationMillis,
                singleArc = false,
                endValue = if (percVal2 != 1f) {
                    percVal2 - percPadding / 2
                } else {
                    percVal2
                }
            )
        }
    }
}

@Composable
private fun DrawCircumference(
    diameter: Dp,
    topLeftOffset: Offset = Offset(0f, 0f),
    color: Color = MaterialTheme.colors.primary,
    stroke: Stroke = Stroke(2f),
    startAngle: Float = 0f,
    animated: Boolean = true,
    durationMillis: Int = 2000,
    singleArc: Boolean = true,
    @FloatRange(from = 0.0, to = 1.0) startValue: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) endValue: Float = 1f
) {

    var start = startValue.coerceIn(0f, 1f)
    var end = endValue.coerceIn(0f, 1f)

    if (start.compareTo(end) > 0) {
        start = 0f
        end = 1f
        //TODO pallua better throw or reset values?
        //  throw IllegalArgumentException("animationEndValue = $animationEndValue must be greater than animationStartValue = $animationStartValue !")
    }

    if (animated) {
        val animateFloat = remember { Animatable(start) }

        LaunchedEffect(animateFloat) {
            animateFloat.animateTo(
                targetValue = end,
                animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
            )
        }
        if (singleArc) {
            CanvasSingleArcAnimated(
                color,
                startAngle,
                animateFloat,
                topLeftOffset,
                diameter,
                stroke
            )
        } else {
            CanvasDoubleArcAnimated(
                color,
                startAngle,
                animateFloat,
                topLeftOffset,
                diameter,
                stroke
            )
        }
    } else {
        if (singleArc) {
            CanvasSingleArc(
                color = color,
                startAngle = startAngle,
                endAngle = end,
                topLeftOffset = topLeftOffset,
                diameter = diameter,
                stroke = stroke
            )
        } else {
            CanvasDoubleArc(
                color = color,
                startAngle = startAngle,
                endAngle = end,
                topLeftOffset = topLeftOffset,
                diameter = diameter,
                stroke = stroke
            )
        }
    }


}

// region animated canvas

@Composable
private fun CanvasSingleArcAnimated(
    color: Color,
    startAngle: Float,
    animateFloat: Animatable<Float, AnimationVector1D>?,
    topLeftOffset: Offset,
    diameter: Dp,
    stroke: Stroke,
    degrees: Float = 360f
) {
    val sweepAngle: Float = animateFloat?.let { degrees * animateFloat.value } ?: run { degrees }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
    }
}

@Composable
private fun CanvasDoubleArcAnimated(
    color: Color,
    startAngle: Float,
    animateFloat: Animatable<Float, AnimationVector1D>?,
    topLeftOffset: Offset,
    diameter: Dp,
    stroke: Stroke,
    degrees: Float = 180f
) {
    val sweepAngleFirstArc: Float =
        animateFloat?.let { degrees * animateFloat.value } ?: run { degrees }
    val sweepAngleSecondArc: Float =
        animateFloat?.let { -degrees * animateFloat.value } ?: run { degrees }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngleFirstArc,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngleSecondArc,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
    }
}

// endregion animated canvas

// region not animated canvas

@Composable
private fun CanvasSingleArc(
    color: Color,
    startAngle: Float,
    endAngle: Float,
    topLeftOffset: Offset,
    diameter: Dp,
    stroke: Stroke,
    degrees: Float = 360f
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = degrees * endAngle,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
    }
}

@Composable
private fun CanvasDoubleArc(
    color: Color,
    startAngle: Float,
    endAngle: Float,
    topLeftOffset: Offset,
    diameter: Dp,
    stroke: Stroke,
    degrees: Float = 180f
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = degrees * endAngle,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = -degrees * endAngle,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(diameter.toPx(), diameter.toPx()),
            style = stroke
        )
    }
}

// endregion not animated canvas

@Preview
@Composable
fun CircularStatsIndicatorComponentPrev() {
//    Circumference(200.dp, 2.dp, Color.Red)
//    AnimatedCircumference(200.dp, startAngle = 0f)
//    AnimatedCircleFromRight(value = 80f, diameter = 200.dp)
//    CircularStatsIndicatorComponent(
//        total = 100f,
//        valRightExternalLine = 80f,
//        valLeftExternalLine = 20f
//    )
//    IconWithLabel(color = Color.Red, label = "ciao")
//    StatsColumn(100f, 80f, 20f)
//    StatsIndicator(total = 100f, val1 = 80f, val2 = 20f)
//    StatsIndicator(total = 200f, val1 = 200f, val2 = 0f)

//    StatsIndicator(total = 200f, val1 = 150f, val2 = 50f)
    StatsIndicator(
        total = 200f,
        val1 = 100f,
        val2 = 100f,
        animated = false
    )
}

@Preview
@Composable
fun StatsIndicatorMiniPrev() {
    Column() {
        StatsIndicatorMini(total = 10f, val1 = 2f, val2 = 8f, animated = false)
        StatsIndicatorMini(total = 50f, val1 = 30f, val2 = 20f, animated = false)
        StatsIndicatorMini(total = 100f, val1 = 50f, val2 = 50f, animated = false)
        StatsIndicatorMini(total = 200f, val1 = 150f, val2 = 50f, animated = false)
    }

}
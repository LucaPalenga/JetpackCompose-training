package com.example.jcex.components

import android.content.res.Resources
import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcex.layouts.padding

/***
 * PAllUA - 31/03/22
 */

@Composable
fun CircularStatsIndicatorScreen() {
    StatsIndicator(total = 200f, val1 = 150f, val2 = 50f)
//    CircularStatsIndicatorComponent(
//        total = 200f,
//        rightExternalLineValue = 100f,
//        leftExternalLineValue = 100f,
//        rightExternalLineColor = Color.Blue,
//        leftExternalLineColor = Color.Cyan,
//        innerLineColor = Color.LightGray
//    )
}

val Float.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
val Dp.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density).value


@Composable
fun StatsIndicator(
    modifier: Modifier = Modifier,
    total: Float,
    val1: Float,
    val2: Float,
    totalColor: Color = Color.LightGray,
    val1Color: Color = Color.Blue,
    val2Color: Color = Color.Cyan
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
            modifier = Modifier.padding(20.dp)
        ) {
            TextStatsIndicatorContent(value = total.toInt(), label = "total")
        }
        StatsColumn(
            modifier = Modifier.padding(end = 16.dp),
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
        IconWithLabel(color = totalColor, label = total.toString())
        Text(text = "label val2", maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val2Color, label = val2.toString())
        Text(text = "label val1", maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconWithLabel(color = val1Color, label = val1.toString())
    }
}

@Composable
fun IconWithLabel(color: Color, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .requiredSize(8.dp)
                .background(color)
        )
        Text(modifier = Modifier.padding(start = 8.dp), text = label)
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
    durationMillis: Int = 2000,
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
        AnimatedCircumference(
            diameter = innerDiameter,
            topLeftOffset = innerCircleTopLeftOffset,
            color = innerLineColor,
            stroke = Stroke(width = innerLineStrokeWidth.px, cap = StrokeCap.Round),
            durationMillis = durationMillis
        )

        val percPadding = paddingBetweenExternalCircles.px / total

        //external right line
        if (rightExternalLineValue != 0f) {
            val percVal1 = rightExternalLineValue / total
            AnimatedCircumference(
                diameter = diameter,
                color = rightExternalLineColor,
                stroke = Stroke(width = rightExternalLineStrokeWidth.px, cap = StrokeCap.Round),
                durationMillis = durationMillis,
                animationEndValue = if (percVal1 != 1f) {
                    percVal1 - percPadding / 2
                } else {
                    percVal1
                }
            )
        }

        //external left line
        if (leftExternalLineValue != 0f) {
            val percVal2 = leftExternalLineValue / total
            AnimatedCircumference(
                diameter = leftDiameter,
                topLeftOffset = leftCircleTopLeftOffset,
                color = leftExternalLineColor,
                stroke = Stroke(leftExternalLineStrokeWidth.px, cap = StrokeCap.Round),
                startAngle = -180f,
                durationMillis = durationMillis,
                animationEndValue = if (percVal2 != 1f) {
                    percVal2 - percPadding / 2
                } else {
                    percVal2
                }
            )
        }
    }
}

@Composable
fun AnimatedCircumference(
    diameter: Dp,
    topLeftOffset: Offset = Offset(0f, 0f),
    color: Color = MaterialTheme.colors.primary,
    stroke: Stroke = Stroke(2f),
    startAngle: Float = 0f,
    durationMillis: Int = 2000,
    @FloatRange(from = 0.0, to = 1.0) animationStartValue: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) animationEndValue: Float = 1f,
) {
    var animStart = animationStartValue
    var animEnd = animationEndValue

    if (animStart > animEnd) {
        animStart = 0f
        animEnd = 1f
        //TODO pallua better throw or reset values?
        //  throw IllegalArgumentException("animationEndValue = $animationEndValue must be greater than animationStartValue = $animationStartValue !")
    }

    val animateFloat = remember { Animatable(animStart) }

    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = animEnd,
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = 180f * animateFloat.value,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(
                diameter.toPx(),
                diameter.toPx()
            ),
            style = stroke
        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = -180f * animateFloat.value,
            useCenter = false,
            topLeft = topLeftOffset,
            size = Size(
                diameter.toPx(),
                diameter.toPx()
            ),
            style = stroke
        )
    }
}

@Composable
fun TextStatsIndicatorContent(value: Int, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value.toString())
        Text(text = label)
    }
}

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

    StatsIndicator(total = 200f, val1 = 150f, val2 = 50f)
}


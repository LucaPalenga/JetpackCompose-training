package com.example.jcex.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val MAGIC_FLOAT = 1.2f
private val HairlineBorderStroke = Stroke(Stroke.HairlineWidth)

inline val Float.half: Float
    get() = this / 2

/**
 * Modify element to add border with appearance specified with a [border] and a [shape], pad the
 * content by the [BorderStroke.width] and clip it.
 *
 * @sample androidx.compose.foundation.samples.BorderSample()
 *
 * @param border [BorderStroke] class that specifies border appearance, such as size and color
 * @param shape shape of the border
 */
fun Modifier.border(border: BorderStroke, shape: Shape = RectangleShape) =
    border(width = border.width, brush = border.brush, shape = shape)

/**
 * Returns a [Modifier] that adds border with appearance specified with [width], [color] and a
 * [shape], pads the content by the [width] and clips it.
 *
 * @sample androidx.compose.foundation.samples.BorderSampleWithDataClass()
 *
 * @param width width of the border. Use [Dp.Hairline] for a hairline border.
 * @param color color to paint the border with
 * @param shape shape of the border
 */
fun Modifier.border(width: Dp, color: Color, shape: Shape = RectangleShape) =
    border(width, SolidColor(color), shape)

/**
 * Returns a [Modifier] that adds border with appearance specified with [width], [brush] and a
 * [shape], pads the content by the [width] and clips it.
 *
 * @sample androidx.compose.foundation.samples.BorderSampleWithBrush()
 *
 * @param width width of the border. Use [Dp.Hairline] for a hairline border.
 * @param brush brush to paint the border with
 * @param shape shape of the border
 */
fun Modifier.border(width: Dp, brush: Brush, shape: Shape): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                val outline: Outline = shape.createOutline(size, layoutDirection, this)
                val borderSize = if (width == Dp.Hairline) 1f else width.toPx()

                var insetOutline: Outline? = null // outline used for roundrect/generic shapes
                var stroke: Stroke? = null // stroke to draw border for all outline types
                var pathClip: Path? = null // path to clip roundrect/generic shapes
                var inset = 0f // inset to translate before drawing the inset outline
                // path to draw generic shapes or roundrects with different corner radii
                var insetPath: Path? = null

                val cornerCompensation = width.toPx().half * MAGIC_FLOAT

                if (borderSize > 0 && size.minDimension > 0f) {
                    if (outline is Outline.Rectangle) {
                        stroke = Stroke(borderSize)
                    } else {
                        // Multiplier to apply to the border size to get a stroke width that is
                        // large enough to cover the corners while not being too large to overly
                        // square off the internal shape. The resultant shape will be
                        // clipped to the desired shape. Any value lower will show artifacts in
                        // the corners of shapes. A value too large will always square off
                        // the internal shape corners. For example, for a rounded rect border
                        // a large multiplier will always have squared off edges within the
                        // inner section of the stroke, however, having a smaller multiplier
                        // will still keep the rounded effect for the inner section of the
                        // border
                        val strokeWidth = MAGIC_FLOAT * borderSize
                        inset = borderSize - strokeWidth / 2
                        val insetSize = Size(
                            size.width - inset * 2,
                            size.height - inset * 2
                        )
                        insetOutline = shape.createOutline(insetSize, layoutDirection, this)
                        stroke = Stroke(strokeWidth)
                        pathClip = if (outline is Outline.Rounded) {
                            Path().apply { addRoundRect(outline.roundRect) }
                        } else if (outline is Outline.Generic) {
                            outline.path
                        } else {
                            // should not get here because we check for Outline.Rectangle
                            // above
                            null
                        }

                        insetPath =
                            if (insetOutline is Outline.Rounded &&
                                !insetOutline.roundRect.isSimple
                            ) {
                                // Rounded rect with non equal corner radii needs a path
                                // to be pre-translated
                                Path().apply {
                                    val rect = insetOutline.roundRect
                                    addRoundRect(
                                        RoundRect(
                                            rect.left, rect.top, rect.right, rect.bottom,
                                            CornerRadius(
                                                rect.topLeftCornerRadius.x - cornerCompensation,
                                                rect.topLeftCornerRadius.y - cornerCompensation
                                            )
                                        )
                                    )
                                    translate(Offset(inset, inset))
                                }
                            } else if (insetOutline is Outline.Generic) {
                                // Generic paths must be created and pre-translated
                                Path().apply {
                                    addPath(insetOutline.path, Offset(inset, inset))
                                }
                            } else {
                                // Drawing a round rect with equal corner radii without
                                // usage of a path
                                null
                            }
                    }
                }

                onDrawWithContent {
                    drawContent()
                    // Only draw the border if a have a valid stroke parameter. If we have
                    // an invalid border size we will just draw the content
                    if (stroke != null) {
                        if (insetOutline != null && pathClip != null) {
                            val isSimpleRoundRect = insetOutline is Outline.Rounded &&
                                    insetOutline.roundRect.isSimple
                            withTransform({
                                clipPath(pathClip)
                                // we are drawing the round rect not as a path so we must
                                // translate ourselves othe
                                if (isSimpleRoundRect) {
                                    translate(inset, inset)
                                }
                            }) {
                                if (isSimpleRoundRect) {
                                    // If we don't have an insetPath then we are drawing
                                    // a simple round rect with the corner radii all identical
                                    val rrect = (insetOutline as Outline.Rounded).roundRect
                                    drawRoundRect(
                                        brush = brush,
                                        topLeft = Offset(rrect.left, rrect.top),
                                        size = Size(rrect.width, rrect.height),
                                        cornerRadius = CornerRadius(
                                            rrect.topLeftCornerRadius.x - cornerCompensation,
                                            rrect.topLeftCornerRadius.y - cornerCompensation
                                        ),
                                        style = stroke
                                    )
                                } else if (insetPath != null) {
                                    drawPath(insetPath, brush, style = stroke)
                                }
                            }
                            // Clip rect to ensure the stroke does not extend the bounds
                            // of the composable.
                            clipRect {
                                // Draw a hairline stroke to cover up non-anti-aliased pixels
                                // generated from the clip
                                if (isSimpleRoundRect) {
                                    val rrect = (outline as Outline.Rounded).roundRect
                                    drawRoundRect(
                                        brush = brush,
                                        topLeft = Offset(rrect.left, rrect.top),
                                        size = Size(rrect.width, rrect.height),
                                        cornerRadius = rrect.topLeftCornerRadius,
                                        style = HairlineBorderStroke
                                    )
                                } else {
                                    drawPath(pathClip, brush = brush, style = HairlineBorderStroke)
                                }
                            }
                        } else {
                            // Rectangular border fast path
                            val strokeWidth = stroke.width
                            val halfStrokeWidth = strokeWidth / 2
                            drawRect(
                                brush = brush,
                                topLeft = Offset(halfStrokeWidth, halfStrokeWidth),
                                size = Size(
                                    size.width - strokeWidth,
                                    size.height - strokeWidth
                                ),
                                style = stroke
                            )
                        }
                    }
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = shape
    }
)

@Preview(name = "Preview Bold Borders", widthDp = 180)
@Composable
fun PreviewBorders() {
    Column {

        val textModifier = Modifier
            .padding(top = 16.dp, start = 16.dp)

        Text(
            text = "Corrected Border",
            modifier = textModifier,
        )

        val padding = 16.dp
        val borderWidth = 12.dp
        val color = Color.Black
        val shape = RoundedCornerShape(16.dp)
        val height = 50.dp

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

        Divider()

        Text(
            text = "Original Border",
            modifier = textModifier,
        )

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

    }
}

@Preview(name = "Preview Thin Borders", widthDp = 180)
@Composable
fun PreviewBorders2() {
    Column {

        val textModifier = Modifier
            .padding(top = 16.dp, start = 16.dp)

        Text(
            text = "Corrected Border",
            modifier = textModifier,
        )

        val padding = 16.dp
        val borderWidth = 4.dp
        val color = Color.Black
        val shape = RoundedCornerShape(16.dp)
        val height = 50.dp

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

        Divider()

        Text(
            text = "Original Border",
            modifier = textModifier,
        )

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

    }
}

@Preview(name = "Preview Thick Borders Fully Rounded", widthDp = 180)
@Composable
fun PreviewBorders3() {
    Column {

        val textModifier = Modifier
            .padding(top = 16.dp, start = 16.dp)

        Text(
            text = "Corrected Border",
            modifier = textModifier,
        )

        val padding = 16.dp
        val borderWidth = 4.dp
        val color = Color.Black
        val shape = RoundedCornerShape(24.dp)
        val height = 48.dp

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

        Divider()

        Text(
            text = "Original Border",
            modifier = textModifier,
        )

        Spacer(
            modifier = Modifier
                .padding(padding)
                .border(borderWidth, color, shape)
                .fillMaxWidth()
                .height(height)
        )

    }
}
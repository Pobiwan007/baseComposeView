package com.basecomposeview.modifier

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/** [clickable] without ripple / indication. */
fun Modifier.noIndicationClickable(onClick: () -> Unit): Modifier =
    clickable(interactionSource = null, indication = null, onClick = onClick)

/** Draws a soft rounded "shadow" strip under the bottom edge of the element. */
fun Modifier.addBottomShadow(shadowColor: Color = Color.Gray, shadowHeightRatio: Float = 6f) = this.then(
    drawBehind {
        val shadowOffset = Offset(shadowHeightRatio, shadowHeightRatio)
        val bottomVerticesY = shadowOffset.y / 3

        val vertices = floatArrayOf(
            size.width + shadowOffset.x , size.height - shadowOffset.y ,
            size.width, size.height + shadowOffset.y,
            0f, size.height + shadowOffset.y ,
            shadowOffset.x.unaryMinus(), size.height - shadowOffset.y,
            0f, size.height - bottomVerticesY,
            size.width, size.height - bottomVerticesY,
        )
        val centerX = size.width / 2
        val centerY = size.height / 2
        val roundedCorners = listOf(
            CornerRounding(0f),
            CornerRounding(size.minDimension, smoothing = 0.1f),
            CornerRounding(size.minDimension, smoothing = 0.1f),
            CornerRounding(0f),
            CornerRounding(0f),
            CornerRounding(0f),
        )
        val roundedPolygonPath = RoundedPolygon(
            vertices = vertices,
            centerX = centerX,
            centerY = centerY,
            perVertexRounding = roundedCorners
        )
            .toPath()
            .asComposePath()
        drawPath(path = roundedPolygonPath, color = shadowColor)
    }
)


fun Modifier.bitmapBackground(bitmap: ImageBitmap, style: DrawStyle = Fill) = this.then(
    drawBehind {
        drawImage(
            image = bitmap,
            style = style,
            topLeft = Offset.Zero
        )
    }
)
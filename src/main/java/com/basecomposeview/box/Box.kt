package com.basecomposeview.box

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedBorderBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    roundedDp: Dp = 16.dp,
    borderColor: Color,
    borderWidth: Dp = 5.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(roundedDp))
            .background(backgroundColor)
            .border(border = BorderStroke(borderWidth, borderColor), shape = RoundedCornerShape(roundedDp)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun TransparentRoundedBox(
    modifier: Modifier = Modifier,
    transparentPercent: Float,
    backgroundColor: Color = Color.White,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    val alpha = remember { transparentPercent.coerceIn(0f, 1f) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor.copy(alpha = alpha)), contentAlignment = contentAlignment
    ) {
        content()
    }
}

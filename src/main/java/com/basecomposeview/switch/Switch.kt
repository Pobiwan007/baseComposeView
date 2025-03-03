package com.basecomposeview.switch

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    trackBrush: Brush = Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)),
    thumbShape: RoundedCornerShape = CircleShape,
    indicatorColor: Color = Color.White
) {

    val transition = updateTransition(targetState = checked, label = "Switch Transition")
    val thumbOffset by transition.animateDp(label = "Thumb Offset") {
        if (it) 24.dp else 0.dp
    }

    Box(
        modifier = modifier
            .width(50.dp)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .height(14.dp)
                .background(
                    brush = if (checked) trackBrush else Brush.linearGradient(
                        listOf(Color(0xFFD1D1D6), Color(0xFFD1D1D6))
                    )
                )
        )
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(30.dp)
                .shadow(elevation = 10.dp, shape = thumbShape)
                .background(indicatorColor)
        )
    }
}
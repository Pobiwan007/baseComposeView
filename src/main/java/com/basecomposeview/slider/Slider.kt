/*
package com.basecomposeview.slider

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(
    progress: Float,
    onProgressChanged: (Float) -> Unit,
    trackBrush: Brush = Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)),
    thumbShape: RoundedCornerShape = CircleShape,
    indicatorColor: Color = Color.White,
    thumbSize: Dp = 30.dp
) {
    val transition = updateTransition(targetState = progress, label = "Slider Transition")
    val thumbOffset by transition.animateDp(label = "Thumb Offset") {
        progress * (100.dp) // scale thumb offset to the width of the slider
    }

    Box(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(trackBrush)
            .padding(horizontal = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(thumbSize)
                .shadow(elevation = 10.dp, shape = thumbShape)
                .background(indicatorColor)
                .clickable { onProgressChanged(progress + 0.1f) } // Change progress on click
        )

        // Optionally add a Slider for touch interaction
        Slider(
            value = progress,
            onValueChange = { onProgressChanged(it) },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterStart),
            colors = SliderDefaults.colors(
                thumbColor = indicatorColor,
                activeTrackColor = trackBrush
            )
        )
    }
}*/

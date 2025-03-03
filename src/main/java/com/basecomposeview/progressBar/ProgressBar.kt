package com.basecomposeview.progressBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.basecomposeview.theme.BaseTypography

@Composable
fun LabeledCircularProgressIndicator(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = BaseTypography.bodyMedium,
    strokeWidth: Dp = 5.dp,
    progressIndicatorColor: Color
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = progressIndicatorColor,
            strokeWidth = strokeWidth
        )
        Text(
            modifier = Modifier.padding(strokeWidth),
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HorizontalViewPagerIndicator(
    modifier: Modifier = Modifier,
    viewPagerState: PagerState,
    selectedColor: Color,
    pageCount: Int,
    deselectedColor: Color = Color.White
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(pageCount) { index ->
            val isSelected = viewPagerState.currentPage % pageCount == index
            Spacer(
                modifier = Modifier
                    .size(if(isSelected) 15.dp else 10.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) selectedColor else deselectedColor)
            )
        }
    }
}
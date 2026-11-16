package com.basecomposeview.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basecomposeview.converters.dpToPx

/** Font size presets expressed as a fraction of the screen width in pixels. */
sealed class TextType(val ratio: Float) {
    data object Body : TextType(0.014f)
    data object SmallBody : TextType(0.012f)
    data object ExtraSmallBody : TextType(0.010f)
    data object BodyLarge : TextType(0.015f)
    data object Title : TextType(0.03f)
    data object SmallTitle : TextType(0.019f)
    data object MediumTitle : TextType(0.023f)
}

object Text {
    /**
     * Font size that scales with the screen width, so layouts keep their proportions
     * on small phones and tablets. Recomputed on configuration changes (rotation,
     * split screen, foldables).
     */
    @Composable
    fun size(textType: TextType = TextType.MediumTitle): TextUnit {
        val screenWidthPx = dpToPx(LocalConfiguration.current.screenWidthDp.dp)
        return remember(textType, screenWidthPx) { scaledFontSize(screenWidthPx, textType).sp }
    }
}

internal fun scaledFontSize(screenWidthPx: Float, textType: TextType): Float = screenWidthPx * textType.ratio

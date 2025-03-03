package com.basecomposeview.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basecomposeview.converters.dpToPx
import com.common.sharedPreference.BaseSharedPreference

sealed class TextType(val ratio: Float, val key: String) {
    data object Body : TextType(0.014f, "BodyFontSize")
    data object SmallBody : TextType(0.012f, "SmallBodyFontSize")
    data object ExtraSmallBody : TextType(0.010f, "ExtraSmallBodyFontSize")
    data object BodyLarge : TextType(0.015f, "BodyLargeFontSize")
    data object Title : TextType(0.03f, "TitleFontSize")
    data object SmallTitle : TextType(0.019f, "SmallTitleFontSize")
    data object MediumTitle : TextType(0.023f, "MediumTitleFontSize")
}

object Text {
    @Composable
    fun size(textType: TextType = TextType.MediumTitle): TextUnit {
        val sp = BaseSharedPreference.newInstance(LocalContext.current)

        val savedSize = sp.getFloat(textType.key, -1f)
        if (savedSize != -1f) {
            return savedSize.sp
        }

        val configuration = LocalConfiguration.current
        val screenWidth = dpToPx(configuration.screenWidthDp.dp)

        return remember(textType) {
            (screenWidth * textType.ratio).also {
                sp.putFloat(textType.key, it)
            }.sp
        }
    }
}

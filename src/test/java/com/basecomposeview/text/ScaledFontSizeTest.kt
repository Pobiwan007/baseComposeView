package com.basecomposeview.text

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ScaledFontSizeTest {

    @Test
    fun `font size is proportional to screen width`() {
        assertEquals(10.8f, scaledFontSize(1080f, TextType.ExtraSmallBody), 0.001f)
        assertEquals(2 * scaledFontSize(720f, TextType.Title), scaledFontSize(1440f, TextType.Title), 0.001f)
    }

    @Test
    fun `presets keep their visual hierarchy`() {
        val ordered = listOf(
            TextType.ExtraSmallBody,
            TextType.SmallBody,
            TextType.Body,
            TextType.BodyLarge,
            TextType.SmallTitle,
            TextType.MediumTitle,
            TextType.Title
        ).map { scaledFontSize(1080f, it) }

        assertTrue(ordered.zipWithNext().all { (a, b) -> a < b })
    }
}

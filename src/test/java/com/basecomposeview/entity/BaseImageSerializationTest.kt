package com.basecomposeview.entity

import com.basecomposeview.entity.image.BaseImage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

/** BaseImage is passed between type-safe navigation destinations as JSON. */
class BaseImageSerializationTest {

    @Test
    fun `url image survives a json round trip`() {
        val image: BaseImage = BaseImage.Url("https://example.com/a.png?size=large&x=1")
        assertEquals(image, Json.decodeFromString<BaseImage>(Json.encodeToString(image)))
    }

    @Test
    fun `resource image survives a json round trip`() {
        val image: BaseImage = BaseImage.Resource(resId = 0x7f080001)
        assertEquals(image, Json.decodeFromString<BaseImage>(Json.encodeToString(image)))
    }
}

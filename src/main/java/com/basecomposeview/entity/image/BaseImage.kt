package com.basecomposeview.entity.image

import kotlinx.serialization.Serializable

@Serializable
sealed interface BaseImage {
    @Serializable
    data class Resource(val resId: Int): BaseImage

    @Serializable
    data class Url(val url: String): BaseImage

}
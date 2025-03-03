package com.basecomposeview.bottomNav

import kotlinx.serialization.Serializable

@Serializable
abstract class BaseRoute (
    val iconRes: Int? = null,
    val nameRes: Int? = null
)
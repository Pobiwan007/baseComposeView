package com.basecomposeview.entity.image

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val BaseImageType = object : NavType<BaseImage>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): BaseImage? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }


    override fun put(bundle: Bundle, key: String, value: BaseImage) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun parseValue(value: String): BaseImage = Json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: BaseImage): String = Uri.encode(Json.encodeToString(value))
}

val BaseImageURLType = object : NavType<BaseImage.Url>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): BaseImage.Url? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }


    override fun put(bundle: Bundle, key: String, value: BaseImage.Url) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun parseValue(value: String): BaseImage.Url = Json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: BaseImage.Url): String = Uri.encode(Json.encodeToString(value))
}
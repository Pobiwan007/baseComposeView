package com.basecomposeview.image

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.basecomposeview.entity.image.BaseImage
import java.io.IOException

/**
 * Renders a [BaseImage]: a tinted drawable resource or a remote image loaded with Coil.
 * Renders nothing for `null`.
 */
@Composable
fun BaseImageView(
    baseImage: BaseImage?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.White
) {
    when (baseImage) {
        is BaseImage.Resource -> Icon(
            modifier = modifier,
            tint = tint,
            painter = painterResource(baseImage.resId),
            contentDescription = contentDescription
        )
        is BaseImage.Url -> AsyncImage(
            modifier = modifier,
            model = baseImage.url,
            contentDescription = contentDescription
        )
        null -> Unit
    }
}

@Composable
fun AsyncImageWithPlaceholder(
    modifier: Modifier = Modifier,
    url: String,
    error: @Composable () -> Unit,
    placeholder: @Composable () -> Unit
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        loading = {
            placeholder()
        },
        error = {
            error()
        }
    )
}

@Composable
fun AssetImage(
    modifier: Modifier = Modifier,
    bitmap: ImageBitmap? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

@Composable
fun bitmapByFileName(fileName: String): ImageBitmap? {
    val context = LocalContext.current
    return remember(fileName) {
        try {
            context.assets.open(fileName).use { stream ->
                BitmapFactory.decodeStream(stream)?.asImageBitmap()
            }
        } catch (e: IOException) {
            null
        }
    }
}

@Composable
fun ImageContainer(modifier: Modifier = Modifier, imageBitmap: ImageBitmap){
    Image(
        bitmap = imageBitmap,
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
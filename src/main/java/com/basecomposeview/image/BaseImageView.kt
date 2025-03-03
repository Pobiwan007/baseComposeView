package com.basecomposeview.image

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.basecomposeview.entity.image.BaseImage
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BaseImageView(modifier: Modifier = Modifier, baseImage: MutableState<BaseImage?>) {
    if (baseImage.value is BaseImage.Resource) {
        val image = (baseImage.value as BaseImage.Resource)
        Icon(
            modifier = modifier,
            tint = Color.White,
            painter = painterResource(image.resId),
            contentDescription = image.resId.toString()
        )
    } else if (baseImage.value is BaseImage.Url) {
        AsyncImage(
            modifier = modifier,
            model = (baseImage.value as BaseImage.Url).url,
            contentDescription = ""
        )
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
            val inputStream = context.assets.open(fileName)
            BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
        } catch (e: Exception) {
            null
        }
    }
}

@Composable
fun ImageCropperAssets(
    modifier: Modifier = Modifier,
    crop: MutableState<Boolean>,
    isCropping: MutableState<Boolean>,
    imageBitmap: ImageBitmap? = null,
    contentScale: ContentScale = ContentScale.Fit,
    croppedImage: MutableStateFlow<ImageBitmap?>
) {
    val handleSize: Float = LocalDensity.current.run { 60.dp.toPx() }

    imageBitmap?.let {
        ImageCropper(
            modifier = modifier,
            imageBitmap = imageBitmap,
            contentDescription = "Image Cropper",
            cropStyle = CropDefaults.style(drawGrid = false),
            cropProperties = CropDefaults.properties(
                cropOutlineProperty = CropOutlineProperty(
                    OutlineType.Rect,
                    RectCropShape(0, "Rect"),
                ), handleSize = handleSize, pannable = false, contentScale = contentScale,
            ),

            crop = crop.value,
            onCropStart = {
                isCropping.value = true
            },
            onCropSuccess = {
                croppedImage.value = it
                crop.value = false
                isCropping.value = false
            }
        )
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
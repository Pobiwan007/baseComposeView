package com.basecomposeview.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.basecomposeview.converters.dpToPx
import com.basecomposeview.converters.pxToDp
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerHandlerView(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
    handlerWidthPx: Float = dpToPx(LocalConfiguration.current.screenWidthDp.dp) * 0.04f,
    handlerBackground: Brush,
    handlerPaddingTop: Dp = 0.dp,
    handlerState: State<Boolean> = remember { mutableStateOf(true) },
    handlerFractionHeight: Float = 0.75f,
    handler: @Composable () -> Unit
) {
    val drawerWidthPx = dpToPx(LocalConfiguration.current.screenWidthDp.dp - 56.dp)

    var offsetX by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(drawerState) {
        snapshotFlow { drawerState.offset }
            .collect { currentOffset ->
                offsetX = if (abs(currentOffset) < drawerWidthPx)
                    currentOffset + drawerWidthPx
                else
                    0f
            }
    }
    LaunchedEffect(handlerState.value) {
        drawerState.close()
    }

    ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = handlerState.value,
        drawerContent = { drawerContent() },
        content = {
            content()
            if(handlerState.value) {
                Box(
                    modifier = Modifier
                        .padding(top = handlerPaddingTop)
                        .offset { IntOffset(x = offsetX.roundToInt(), y = 0) }
                        .fillMaxHeight(handlerFractionHeight)
                        .width(pxToDp(handlerWidthPx))
                        .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                        .background(brush = handlerBackground)
                ) {
                    handler()
                }
            }
        }
    )
}
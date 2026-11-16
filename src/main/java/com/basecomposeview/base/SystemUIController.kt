package com.basecomposeview.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * Hides / shows system bars while this composable is in the composition and restores
 * them when it leaves. Bars can be revealed temporarily with a swipe.
 */
@Composable
fun SystemUiController(
    hideStatusBar: Boolean = true,
    hideNavBar: Boolean = true,
    darkStatusBarIcons: Boolean = true
) {
    val view = LocalView.current
    if (view.isInEditMode) return // Android Studio preview has no window
    val window = view.context.findActivity()?.window ?: return

    DisposableEffect(window, hideStatusBar, hideNavBar, darkStatusBarIcons) {
        val controller = WindowCompat.getInsetsController(window, view)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        if (hideNavBar) controller.hide(WindowInsetsCompat.Type.navigationBars())
        if (hideStatusBar) {
            controller.hide(WindowInsetsCompat.Type.statusBars())
        } else {
            controller.isAppearanceLightStatusBars = darkStatusBarIcons
        }

        onDispose {
            controller.show(WindowInsetsCompat.Type.systemBars())
        }
    }
}

/** Unwraps [ContextWrapper]s (e.g. ContextThemeWrapper, Hilt's wrapper) to find the hosting Activity. */
internal tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

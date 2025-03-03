package com.basecomposeview.base

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SystemUiController(
    hideStatusBar: Boolean = true,
    hideNavBar: Boolean = true,
    darkStatusBarIcons: Boolean = true
) {
    val context = LocalContext.current
    val window = (context as ComponentActivity).window
    val insetsController = WindowInsetsControllerCompat(window, LocalView.current)
    DisposableEffect(Unit) {
        // Set the system UI to be immersive and hide the navigation bar
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        //insetsController.hide(android.view.WindowInsets.Type.systemBars())
        if (hideNavBar)
            insetsController.hide(WindowInsetsCompat.Type.navigationBars())
        if (hideStatusBar) {
            insetsController.hide(WindowInsetsCompat.Type.statusBars())
        } else {
            insetsController.isAppearanceLightStatusBars = darkStatusBarIcons
        }
        onDispose {
            // Optionally, you could reset the UI on dispose if needed
            insetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }
}

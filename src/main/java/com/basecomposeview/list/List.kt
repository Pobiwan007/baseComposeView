package com.basecomposeview.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Shows one item at a time and cycles through [items] when [navigateAction] is set
 * to [NavigationAction.Previous] or [NavigationAction.Next]. The action is consumed
 * (reset to `null`) after it has been handled.
 */
@Composable
fun <T> NavigationListView(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable (index: Int) -> Unit,
    navigateAction: MutableState<NavigationAction?>,
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val action = navigateAction.value

    // State must not be written during composition: handle the event in an effect.
    LaunchedEffect(action) {
        if (action != null) {
            currentIndex = action.nextIndex(currentIndex, items.size)
            navigateAction.value = null
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (currentIndex in items.indices) content(currentIndex)
    }
}

/** Index after applying this action to a list of [size] items, wrapping around at both ends. */
fun NavigationAction.nextIndex(current: Int, size: Int): Int {
    if (size <= 0) return 0
    return when (this) {
        NavigationAction.Previous -> if (current > 0) current - 1 else size - 1
        NavigationAction.Next -> if (current < size - 1) current + 1 else 0
    }
}

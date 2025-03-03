package com.basecomposeview.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> NavigationListView(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable (item: Int) -> Unit,
    navigateAction: MutableState<NavigationAction?>,
) {
    var currentIndex by remember { mutableIntStateOf(0) }
        when (navigateAction.value) {
            is NavigationAction.Previous -> {
                if (currentIndex > 0) {
                    currentIndex--
                } else {
                    currentIndex = items.size - 1
                }
                navigateAction.value = null
            }


            is NavigationAction.Next -> {
                if (currentIndex < items.size - 1) {
                    currentIndex++
                } else {
                    currentIndex = 0
                }
                navigateAction.value = null
            }

            null -> {

            }
        }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items.getOrNull(currentIndex)?.let {
            content(currentIndex)
        }
    }
}

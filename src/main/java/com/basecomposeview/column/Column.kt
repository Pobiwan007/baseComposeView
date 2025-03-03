package com.basecomposeview.column

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun SpaceBetweenColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun CenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}
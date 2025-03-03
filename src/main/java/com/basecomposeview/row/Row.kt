package com.basecomposeview.row

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SpaceBetweenRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        content()
    }
}

@Composable
fun SpaceAroundRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        content()
    }
}

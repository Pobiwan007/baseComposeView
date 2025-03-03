package com.basecomposeview.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.common.RootError
import com.common.Result

@Composable
inline fun <D, E : RootError> BaseResultView(
    modifier: Modifier = Modifier,
    response: Result<D, E>,
    alignment: Alignment = Alignment.Center,
    crossinline onSuccessful: @Composable (data: Result.Success<D, E>) -> Unit,
    crossinline onError: @Composable (error: Result.Error<D, E>) -> Unit = {},
    crossinline onLoading: @Composable () -> Unit = {}
) {
    Box(modifier = modifier, contentAlignment = alignment) {
        when (response) {
            is Result.Error -> {
                onError(response)
            }

            Result.Loading -> {
                onLoading()
            }

            is Result.Success -> {
                onSuccessful(response)
            }
        }
    }
}

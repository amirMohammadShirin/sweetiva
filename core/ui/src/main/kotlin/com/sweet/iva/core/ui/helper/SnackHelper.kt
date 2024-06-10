package com.sweet.iva.core.ui.helper

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.staticCompositionLocalOf
import com.sweet.iva.core.ui.entity.DisplayedError


val LocalSnackBarState = staticCompositionLocalOf<SnackbarHostState> {
    error("snackbar not provided")
}


suspend fun SnackbarHostState.showSnackbar(
    displayedError: DisplayedError.SnackBarError,
    onResult: ((result: SnackbarResult) -> Unit)? = null,
) {
    val result = showSnackbar(
        displayedError.displayedMessage,
    )
    onResult?.invoke(result)
}
//
//modifier: Modifier = Modifier,
//action: (@Composable () -> Unit)? = null,
//dismissAction: (@Composable () -> Unit)? = null,
//actionOnNewLine: Boolean = false,
//shape: Shape = SnackbarDefaults.shape,
//content: @Composable () -> Unit
//containerColor: Color = SnackbarDefaults.color,
//contentColor: Color = SnackbarDefaults.contentColor,
//actionContentColor: Color = SnackbarDefaults.actionContentColor,
//dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,

fun Context.showToast(displayedError: DisplayedError.ToastError) {
    Toast.makeText(this, displayedError.displayedMessage, Toast.LENGTH_SHORT).show()
}
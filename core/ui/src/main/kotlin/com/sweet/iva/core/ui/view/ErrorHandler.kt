package com.sweet.iva.core.ui.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.sweet.iva.core.ui.entity.DisplayedError
import com.sweet.iva.core.ui.helper.LocalSnackBarState
import com.sweet.iva.core.ui.helper.showSnackbar
import com.sweet.iva.core.ui.helper.showToast
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

interface ErrorHandler {
    @SuppressLint("NotConstructor")
    @Composable
    fun ErrorHandler(viewModel: BaseViewModel<*, *>)
}

class ErrorHandlerImpl : ErrorHandler {
    @Composable
    override fun ErrorHandler(viewModel: BaseViewModel<*, *>) {
        val snackbarHostState = LocalSnackBarState.current
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.errorFlow.collect {
                when (it) {
                    is DisplayedError.SnackBarError -> {
                        snackbarHostState.showSnackbar(displayedError = it)
                    }

                    is DisplayedError.ToastError -> {
                        context.showToast(it)
                    }
                }
            }
        }
    }
}
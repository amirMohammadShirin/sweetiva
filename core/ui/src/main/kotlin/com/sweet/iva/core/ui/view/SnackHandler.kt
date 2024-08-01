package com.sweet.iva.core.ui.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.sweet.iva.core.ui.helper.LocalSnackBarState
import com.sweet.iva.core.ui.model.Event
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

interface SnackHandler {
    @SuppressLint("NotConstructor")
    @Composable
    fun SnackHandler(viewModel: BaseViewModel<*, *>)
}

class SnackHandlerImpl : SnackHandler {
    @Composable
    override fun SnackHandler(viewModel: BaseViewModel<*, *>) {
        val snackbarHostState = LocalSnackBarState.current
        LaunchedEffect(Unit) {
            viewModel.uiEventFlow.collect {
                if (it is Event.ShowSnack) {
                    snackbarHostState.showSnackbar(it.message)
                }
            }
        }
    }
}
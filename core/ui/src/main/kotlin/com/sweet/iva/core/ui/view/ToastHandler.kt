package com.sweet.iva.core.ui.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.sweet.iva.core.ui.helper.showToast
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

interface ToastHandler {
    @SuppressLint("NotConstructor")
    @Composable
    fun ToastHandler(viewModel: BaseViewModel<*, *>)
}

class ToastHandlerImpl : ToastHandler {
    @Composable
    override fun ToastHandler(viewModel: BaseViewModel<*, *>) {
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            viewModel.uiEventFlow.collect {
                if (it is com.sweet.iva.core.ui.model.Event.ShowToast) {
                    context.showToast(it.message)
                }
            }
        }
    }
}
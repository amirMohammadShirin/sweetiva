package com.sweet.iva.core.ui.view

import androidx.compose.runtime.Composable
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

interface BaseScreenBehavior {
    @Composable
    fun ScreenBehavior(viewModel: BaseViewModel<*, *>)
}

class BaseScreenBehaviorImpl : BaseScreenBehavior,
    NavigationHandler by NavigationHandlerImpl(),
    SnackHandler by SnackHandlerImpl(),
    ErrorHandler by ErrorHandlerImpl(),
    ToastHandler by ToastHandlerImpl() {

    @Composable
    override fun ScreenBehavior(viewModel: BaseViewModel<*, *>) {
        NavigationHandler(viewModel)
        SnackHandler(viewModel)
        ErrorHandler(viewModel)
        ToastHandler(viewModel)
    }

}
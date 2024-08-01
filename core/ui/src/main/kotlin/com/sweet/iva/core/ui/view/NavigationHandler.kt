package com.sweet.iva.core.ui.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.sweet.iva.core.ui.helper.LocalNavController
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

interface NavigationHandler {
    @SuppressLint("NotConstructor")
    @Composable
    fun NavigationHandler(viewModel: BaseViewModel<*, *>)
}

class NavigationHandlerImpl : NavigationHandler {
    @Composable
    override fun NavigationHandler(viewModel: BaseViewModel<*, *>) {
        val navController = LocalNavController.current
        LaunchedEffect(Unit) {
            viewModel.navigationFlow.collect {
                it.navigate(navController)
            }
        }
    }
}
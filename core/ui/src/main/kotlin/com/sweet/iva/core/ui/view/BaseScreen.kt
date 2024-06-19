package com.sweet.iva.core.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.sweet.iva.core.ui.entity.DisplayedError
import com.sweet.iva.core.ui.helper.LocalNavController
import com.sweet.iva.core.ui.helper.LocalSnackBarState
import com.sweet.iva.core.ui.helper.getComposableState
import com.sweet.iva.core.ui.helper.showSnackbar
import com.sweet.iva.core.ui.helper.showToast
import com.sweet.iva.core.ui.model.IAction
import com.sweet.iva.core.ui.model.IEvent
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

abstract class BaseScreen<State, Action : IAction, Event : IEvent>(
    val route: String,
    val name: String
) {

    protected val parameters = linkedMapOf<NavigationParam, String>()

    @Composable
    abstract fun viewModel(): BaseViewModel<State, Action, Event>

    @Composable
    open fun Screen(navBackStackEntry: NavBackStackEntry?) {
        with(viewModel()) {

            navBackStackEntry?.let {
                initParameters(navBackStackEntry)
            }

            val state = getComposableState()
            BaseScreenBehavior(this) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Content(state.value)
                }
            }

        }
    }

    private fun initParameters(navBackStackEntry: NavBackStackEntry) {
        navBackStackEntry.arguments?.let {
            it.keySet().forEach { key ->
                NavigationParam.getByName(key)?.let { param ->
                    parameters[param] = it.getString(key) ?: ""
                }
            }
        }
    }

    @Composable
    protected fun HandleEvent(
        onEvent: (IEvent) -> Unit
    ) {
        with(viewModel()) {
            LaunchedEffect(key1 = Unit) {
                uiEventFlow.collect {
                    onEvent.invoke(it)
                }
            }
        }
    }


    @Composable
    abstract fun Content(state: State)


    /**
     * this composable must be called
     * in the chain normal application flow
     * because it needs "LocalSnackBarState.current"
     * @see com.example.mark2.helper.LocalSnackBarState
     * @throws IllegalStateException when the snackbarHostState
     * not provided in call site
     */
    @Composable
    private fun BaseScreenBehavior(
        viewModel: BaseViewModel<State, Action, Event>,
        snackbarHostState: SnackbarHostState = LocalSnackBarState.current,
        context: Context = LocalContext.current,
        feature: (@Composable () -> Unit)
    ) {
        NavigationHandler(
            viewModel = viewModel,
            navController = LocalNavController.current
        ) {
            ErrorHandler(
                viewModel = viewModel,
                snackbarHostState = snackbarHostState,
                context = context
            ) {
                SnackHandler(viewModel = viewModel, snackbarHostState = snackbarHostState) {
                    ToastHandler(
                        viewModel = viewModel,
                        context = context
                    ) {
                        feature.invoke()
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationHandler(
        viewModel: BaseViewModel<State, Action, Event>,
        navController: NavController,
        content: (@Composable () -> Unit)
    ) {
        LaunchedEffect(Unit) {
            viewModel.navigationFlow.collect {
                it.navigate(navController)
            }
        }
        content.invoke()
    }


    @Composable
    fun ErrorHandler(
        viewModel: BaseViewModel<*, out IAction, *>,
        snackbarHostState: SnackbarHostState,
        context: Context,
        content: (@Composable () -> Unit)
    ) {
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
        content.invoke()
    }

    @Composable
    fun SnackHandler(
        viewModel: BaseViewModel<*, out IAction, *>,
        snackbarHostState: SnackbarHostState,
        content: (@Composable () -> Unit)
    ) {
        LaunchedEffect(Unit) {
            viewModel.uiEventFlow.collect {
                if (it is IEvent.ShowSnack) {
                    snackbarHostState.showSnackbar(it.message)
                }
            }
        }
        content.invoke()
    }

    @Composable
    fun ToastHandler(
        viewModel: BaseViewModel<*, out IAction, *>,
        context: Context,
        content: (@Composable () -> Unit)
    ) {
        LaunchedEffect(Unit) {
            viewModel.uiEventFlow.collect {
                if (it is IEvent.ShowToast) {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        content.invoke()
    }

}
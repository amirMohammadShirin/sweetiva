package com.sweet.iva.core.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.sweet.iva.core.ui.helper.getComposableState
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

abstract class BaseScreen<State, Event : com.sweet.iva.core.ui.model.Event>(
    val route: String,
    val name: String
) : BaseScreenBehavior by BaseScreenBehaviorImpl() {

    protected val parameters = linkedMapOf<NavigationParam, String>()

    @Composable
    abstract fun viewModel(): BaseViewModel<State, Event>

    @Composable
    open fun Screen(navBackStackEntry: NavBackStackEntry?) {
        with(viewModel()) {

            navBackStackEntry?.let {
                initParameters(navBackStackEntry)

            }

            ScreenBehavior(viewModel())

            val state = getComposableState()
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Content(state.value)
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
        onEvent: (com.sweet.iva.core.ui.model.Event) -> Unit
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


}
package com.sweet.iva.core.ui.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sweet.iva.core.ui.helper.getComposableState
import com.sweet.iva.core.ui.viewmodel.BaseViewModel

abstract class ViewComponent<State, Event : com.sweet.iva.core.ui.model.Event> {

    @Composable
    protected abstract fun viewModel(): BaseViewModel<State, Event>

    @Composable
    fun Compose(modifier: Modifier) {
        with(viewModel()) {

            val state = getComposableState()

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Content(state.value)
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
    protected abstract fun Content(state: State)


}
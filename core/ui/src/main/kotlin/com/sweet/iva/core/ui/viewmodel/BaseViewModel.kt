package com.sweet.iva.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweet.iva.core.ui.entity.DisplayedError
import com.sweet.iva.core.ui.model.IAction
import com.sweet.iva.core.ui.model.IEvent
import com.sweet.iva.core.ui.navigation.NavigationCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by aShirin on 12/27/2023.
 */


abstract class BaseViewModel<State, Action : IAction, Event : IEvent>(
    val initialState: State
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow: StateFlow<State>
        get() = _uiStateFlow

    val currentState: State
        get() = _uiStateFlow.value

    private val _uiActionFlow = MutableSharedFlow<Action>()

    private val _uiEventFlow = MutableSharedFlow<IEvent>()
    val uiEventFlow: SharedFlow<IEvent>
        get() = _uiEventFlow

    private val _navigationFlow = MutableSharedFlow<NavigationCommand>()
    val navigationFlow: Flow<NavigationCommand> = _navigationFlow

    private val _errorFlow: MutableSharedFlow<DisplayedError> = MutableSharedFlow()
    val errorFlow: Flow<DisplayedError>
        get() = _errorFlow

    abstract fun handleAction(action: Action)

    init {
        viewModelScope.launch {
            _uiActionFlow.collect(this@BaseViewModel::handleAction)
        }
    }

    fun process(action: Action) {
        viewModelScope.launch {
            _uiActionFlow.emit(action)
        }
    }


    fun updateState(mutation: (currentState: State) -> State) {
        _uiStateFlow.update {
            mutation.invoke(it)
        }
    }

    fun sendEvent(event: IEvent) {
        viewModelScope.launch {
            _uiEventFlow.emit(event)
        }
    }

    fun navigateTo(command: NavigationCommand.ToScreen) {
        viewModelScope.launch {
            _navigationFlow.emit(command)
        }
    }

    fun navigateTo(command: NavigationCommand.ToWithData) {
        viewModelScope.launch {
            _navigationFlow.emit(command)
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigationFlow.emit(NavigationCommand.Back)
        }
    }

}
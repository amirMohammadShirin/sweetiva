package com.sweet.iva.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.user.ListenToCurrentUserUseCase
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.main.model.MainEvent
import com.sweet.iva.main.model.MainViewState
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    private val listenToCurrentUserUseCase: ListenToCurrentUserUseCase,
) : BaseViewModel<MainViewState, MainEvent>(initialState = MainViewState()) {

    fun start() {
        viewModelScope.launch {
            collectCurrentUser()
        }
    }

    private fun collectCurrentUser() {
        viewModelScope.launch {
            listenToCurrentUserUseCase
                .start(null)
                .buffer()
                .collectLatest { user ->
                    navigate(user)
                }
        }
    }

    private fun navigate(currentUser: User?) {
        val destination =
            if (currentUser?.identity != null) ApplicationRoutes.homeGraphRoute else ApplicationRoutes.introGraphRoute

        updateState {
            it.copy(
                startDestination = destination,
                user = currentUser
            )
        }
        hideSplash()
    }

    private fun hideSplash() {
        updateState {
            it.copy(
                loading = false
            )
        }
    }
}

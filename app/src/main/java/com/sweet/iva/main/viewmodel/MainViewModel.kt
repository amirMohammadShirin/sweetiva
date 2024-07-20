package com.sweet.iva.main.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.user.StreamCurrentUserUseCase
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.main.model.MainAction
import com.sweet.iva.main.model.MainEvent
import com.sweet.iva.main.model.MainViewState
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel
    @Inject
    constructor(
        private val dispatcherProvider: DispatcherProvider,
        private val streamCurrentUserUseCase: StreamCurrentUserUseCase,
    ) : BaseViewModel<MainViewState, MainAction, MainEvent>(initialState = MainViewState()) {
        override fun handleAction(action: MainAction) {
            when (action) {
                is MainAction.FetchStartUpData -> {
                    start()
                }
            }
        }

        private fun start() {
            viewModelScope.launch {
                updateState {
                    it.copy(loading = false).also {
                        Log.e("SWEET", "reactive state updated: $it")
                    }
                }
                collectCurrentUser()
            }
        }

        private fun collectCurrentUser() {
            viewModelScope.launch(dispatcherProvider.io) {
                streamCurrentUserUseCase
                    .start(null)
                    .onEach {
                        Log.e("SWEET", "reactive user stream emitted: $it")
                    }.collect { user ->
                        Log.e("SWEET", "reactive user stream collected: $user")
                        navigate(user)
                    }
            }
        }

        fun getStartupData() {
            process(MainAction.FetchStartUpData)
        }

        private fun navigate(currentUser: User?) {
            val destination =
                if (currentUser?.identity != null) ApplicationRoutes.homeGraphRoute else ApplicationRoutes.introGraphRoute

            updateState {
                it
                    .copy(
                        startDestination = destination,
                        user = currentUser,
                    ).also {
                        Log.e("SWEET", "reactive state updated: $it")
                    }
            }
        }
    }

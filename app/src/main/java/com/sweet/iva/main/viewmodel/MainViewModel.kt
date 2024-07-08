package com.sweet.iva.main.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.user.GetCurrentUserUseCase
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.main.model.MainAction
import com.sweet.iva.main.model.MainEvent
import com.sweet.iva.main.model.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class MainViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val reactiveUser: ReactiveCache<User>
) :
    BaseViewModel<MainViewState, MainAction, MainEvent>(initialState = MainViewState()) {

    override fun handleAction(action: MainAction) {
        when (action) {
            is MainAction.FetchStartUpData -> {
                start()
            }
        }
    }

    init {
        collectCurrentUser()
    }

    private fun collectCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            reactiveUser.dataStream().collect { user ->
                updateState {
                    it.copy(user = user)
                }
            }
        }
    }

    fun getStartupData() {
        process(MainAction.FetchStartUpData)
    }

    private fun start() {

        viewModelScope.launch {

            val currentUser = getCurrentUserUseCase.execute(null)
            val startDestination =
                if (currentUser != null) ApplicationRoutes.homeGraphRoute else ApplicationRoutes.introGraphRoute

            withContext(Dispatchers.IO) {
                delay(1000)
            }

            updateState {
                it.copy(
                    loading = false,
                    startDestination = startDestination
                )
            }

        }
    }


}


package com.sweet.iva.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.user.GetCurrentUserUseCase
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.main.model.MainAction
import com.sweet.iva.main.model.MainEvent
import com.sweet.iva.main.model.MainViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) :
    BaseViewModel<MainViewState, MainAction, MainEvent>(initialState = MainViewState()) {

    override fun handleAction(action: MainAction) {
        when (action) {
            is MainAction.FetchStartUpData -> {
                callApi()
            }
        }
    }

    fun getStartupData() {
        process(MainAction.FetchStartUpData)
    }

    private fun callApi() {
        viewModelScope.launch {

            val currentUser = getCurrentUserUseCase.execute(null)

            withContext(Dispatchers.IO) {
                delay(1000)
            }

            updateState {
                it.copy(
                    loading = false,
                    isUserLoggedIn = currentUser != null
                )
            }

        }
    }


}


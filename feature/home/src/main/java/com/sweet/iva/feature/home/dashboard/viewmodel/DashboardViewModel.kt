package com.sweet.iva.feature.home.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.user.LogoutUseCase
import com.sweet.arch.core.domain.usecase.user.StreamCurrentUserUseCase
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.designsystem.component.model.UserCardUiModel
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.model.mockAccounts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val logoutUseCase: LogoutUseCase
) :
    BaseViewModel<DashboardUiModel, DashboardEvent>(
        initialState = DashboardUiModel(),
    ) {

    fun getUserAccounts() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                delay(2000)
            }
            updateState {
                it.copy(
                    userAccounts = mockAccounts,
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute(null)
        }
    }

}

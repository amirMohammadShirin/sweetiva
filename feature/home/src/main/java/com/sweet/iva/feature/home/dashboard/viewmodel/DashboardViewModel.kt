package com.sweet.iva.feature.home.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
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
    private val dispatcherProvider: DispatcherProvider
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

}

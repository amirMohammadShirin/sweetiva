package com.sweet.iva.feature.home.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.user.LogoutUseCase
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val logoutUseCase: LogoutUseCase
) :
    BaseViewModel<DashboardUiModel, DashboardEvent>(
        initialState = DashboardUiModel(),
    ) {

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.execute(null)
        }
    }

}

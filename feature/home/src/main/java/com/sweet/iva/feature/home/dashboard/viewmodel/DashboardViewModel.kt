package com.sweet.iva.feature.home.dashboard.viewmodel

import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() :
    BaseViewModel<DashboardUiModel, DashboardAction, DashboardEvent>(
        initialState = DashboardUiModel()
    ) {

    override fun handleAction(action: DashboardAction) {

    }
}
package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.core.ui.model.IAction

sealed interface DashboardAction : IAction {
    data object GetUserAccounts : DashboardAction
}
package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.core.ui.model.Action

sealed interface DashboardAction : Action {
    data object GetUserAccounts : DashboardAction
}
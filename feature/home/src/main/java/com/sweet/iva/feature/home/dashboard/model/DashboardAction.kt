package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.core.ui.model.IAction

sealed interface DashboardAction : IAction {

    data class PanChanged(val cardIndex: Int, val pan: String) : DashboardAction
    data class NameChanged(val cardIndex: Int, val name: String) : DashboardAction

}
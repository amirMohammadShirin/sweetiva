package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.viewmodel.DashboardViewModel

class DashboardScreen : BaseScreen<DashboardUiModel, DashboardAction, DashboardEvent>(
    route = ApplicationRoutes.dashboardScreenRoute,
    name = "خانه"
) {
    @Composable
    override fun viewModel(): DashboardViewModel = hiltViewModel()

    @Composable
    override fun Content(state: DashboardUiModel) {

        Text(text = "dashboard")

    }
}
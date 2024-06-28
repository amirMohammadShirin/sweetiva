package com.sweet.iva.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.sweet.iva.core.ui.helper.registerDestination
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.feature.home.dashboard.view.DashboardScreen

val dashboardScreen = DashboardScreen()

fun NavGraphBuilder.homeGraph() {

    navigation(
        route = ApplicationRoutes.homeGraphRoute,
        startDestination = ApplicationRoutes.dashboardScreenRoute
    ) {
        registerDestination(dashboardScreen)
    }

}

package com.sweet.iva.core.ui.navigation

import androidx.navigation.NavController

sealed class NavigationCommand(
    val route: String
) {
    abstract fun navigate(navController: NavController)

    class ToScreen(route: String) : NavigationCommand(route) {
        override fun navigate(navController: NavController) {
            navController.navigate(route)
        }
    }

    data object Back : NavigationCommand("") {
        override fun navigate(navController: NavController) {
            navController.popBackStack()
        }

    }

}


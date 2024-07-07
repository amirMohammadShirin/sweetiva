package com.sweet.iva.core.ui.navigation

import androidx.navigation.NavController

sealed class NavigationCommand(
    val route: String? = null
) {
    abstract fun navigate(navController: NavController)

    class ToScreen(
        route: String,
        private val clearTo: String? = null
    ) : NavigationCommand(route) {
        override fun navigate(navController: NavController) {
            route?.let {
                navController.navigate(it) {
                    clearTo?.let {
                        popUpTo(clearTo) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    class ToWithData(
        route: String,
        private val param: LinkedHashMap<NavigationParam, String>
    ) : NavigationCommand(route) {
        override fun navigate(navController: NavController) {
            route?.let {
                var destinationRoute = it
                param.forEach {
                    destinationRoute = destinationRoute.replace("{${it.key}}", it.value)
                }
                navController.navigate(destinationRoute)
            }
        }
    }

    data object Back : NavigationCommand(null) {
        override fun navigate(navController: NavController) {
            navController.popBackStack()
        }

    }

}


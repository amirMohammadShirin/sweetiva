package com.sweet.iva.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.sweet.iva.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMarkAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(
        coroutineScope,
        navController
    ) {
        AppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination
        @Composable get() = TopLevelDestination.findByRoute(currentDestination?.route ?: "")

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        if (!navController.currentDestination?.route.equals(topLevelDestination.route, true))
            trace("Navigation:${topLevelDestination.name}") {
                val navOptions = navOptions {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                navController.navigate(topLevelDestination.route, navOptions = navOptions)
            }
    }

}
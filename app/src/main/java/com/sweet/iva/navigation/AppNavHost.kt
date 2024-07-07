package com.sweet.iva.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sweet.iva.application.AppState
import com.sweet.iva.core.ui.helper.LocalNavController
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.feature.home.homeGraph
import com.sweet.iva.feature.intro.introGraph
import com.sweet.iva.feature.login.loginGraph

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String
) {
    CompositionLocalProvider(LocalNavController provides appState.navController) {
        NavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            introGraph()
            loginGraph()
            homeGraph()
        }
    }
}
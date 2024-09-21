package com.sweet.iva.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sweet.iva.application.AppState
import com.sweet.iva.core.ui.helper.LocalNavController

@Composable
fun AppNavHost(
    startDestination: String,
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalNavController provides appState.navController) {
        NavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {

        }
    }
}

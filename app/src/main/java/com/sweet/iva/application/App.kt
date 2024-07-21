package com.sweet.iva.application

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sweet.arch.core.domain.model.user.User
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppNavigationBar
import com.sweet.iva.core.designsystem.component.AppNavigationBarItem
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.component.TopAppBar
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.ui.helper.LocalSnackBarState
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.navigation.AppNavHost
import com.sweet.iva.navigation.TopLevelDestination

@SuppressLint("UnrememberedMutableState")
@Composable
fun App(
    user: User? = null,
    startDestination: String,
    appState: AppState =
        rememberMarkAppState(
            startDestination = startDestination,
            user = user,
        ),
) {
    val currentUser = mutableStateOf(user)
    val currentRoute = appState.currentDestination?.route ?: ""
    val destination =
        if (currentUser.value?.identity != null) ApplicationRoutes.homeGraphRoute else ApplicationRoutes.introGraphRoute
    val showBottomNavigation =
        (currentUser.value?.identity != null) &&
            TopLevelDestination.isTopLevelDestination(
                currentRoute,
            )

    AppBackground(
        modifier = Modifier,
    ) {
        val snackBarHostState =
            remember {
                SnackbarHostState()
            }

        Scaffold(
            modifier = Modifier,
            topBar = {},
            bottomBar = {
                if (showBottomNavigation) {
                    NavigationBar(
                        destinations = TopLevelDestination.entries.toList(),
                        currentTopLevelDestination = TopLevelDestination.HOME,
                        onItemClicked = {},
                    )
                }
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            snackbarHost = { SnackbarHost(snackBarHostState) },
        ) { padding ->
            CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
                AppNavHost(
                    modifier = Modifier.padding(padding),
                    appState = appState,
                    startDestination = destination,
                )
            }
        }
    }
}

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentTopLevelDestination: TopLevelDestination,
    onItemClicked: (destination: TopLevelDestination) -> Unit,
) {
    AppNavigationBar {
        destinations.forEach { topLevelDestination ->
            AppNavigationBarItem(
                selected = currentTopLevelDestination == topLevelDestination,
                onClick = { onItemClicked.invoke(topLevelDestination) },
                icon = topLevelDestination.unSelectedIcon,
                label = topLevelDestination.title,
                selectedIcon = topLevelDestination.selectedIcon,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
    topLevelDestination: TopLevelDestination,
) {
    AppTheme {
        TopAppBar(
            modifier = modifier,
            title = topLevelDestination.title,
        )
    }
}

@ThemePreviews
@Composable
fun PreviewToolbar() {
    AppTheme {
        Toolbar(topLevelDestination = TopLevelDestination.HOME)
    }
}

@ThemePreviews
@Composable
fun PreviewNavigationBar() {
    AppTheme {
        NavigationBar(
            destinations = TopLevelDestination.entries.toList(),
            currentTopLevelDestination = TopLevelDestination.HOME,
            onItemClicked = {},
        )
    }
}

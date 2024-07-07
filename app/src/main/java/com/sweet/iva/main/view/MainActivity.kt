package com.sweet.iva.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sweet.iva.DarkThemeConfig
import com.sweet.iva.ThemeBrand
import com.sweet.iva.application.App
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.main.model.MainViewState
import com.sweet.iva.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainViewState by mutableStateOf(viewModel.initialState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiStateFlow
                    .onEach {
                        uiState = it
                    }
                    .collect {}
            }
        }


        splashScreen.setKeepOnScreenCondition { uiState.loading }
        viewModel.getStartupData()

        setContent {
            MainActivityContent(uiState)
        }

    }

    @Composable
    private fun MainActivity.MainActivityContent(uiState: MainViewState) {
        if (!uiState.loading) {

            val darkTheme = shouldUserDarkTheme(uiState)

            AppTheme(
                darkTheme = darkTheme,
                androidTheme = shouldUseAndroidTheme(uiState),
                disableDynamicTheming = shouldDisableDynamicTheming(uiState)
            ) {
                AppBackground(modifier = Modifier.fillMaxSize()) {
                    App(startDestination = uiState.startDestination)
                }
            }

        }
    }


    private fun shouldDisableDynamicTheming(uiState: MainViewState): Boolean =
        if (uiState.loading) false else !uiState.useDynamicColor


    private fun shouldUseAndroidTheme(uiState: MainViewState): Boolean =
        if (uiState.loading) false else {
            when (uiState.themeBrand) {
                ThemeBrand.DEFAULT -> false
                ThemeBrand.ANDROID -> true
            }
        }


    @Composable
    private fun shouldUserDarkTheme(uiState: MainViewState): Boolean =
        if (uiState.loading) isSystemInDarkTheme()
        else {
            when (uiState.darkThemeConfig) {
                DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
                DarkThemeConfig.LIGHT -> false
                DarkThemeConfig.DARK -> true
            }
        }


    /**
     * The default light scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

    /**
     * The default dark scrim, as defined by androidx and the platform:
     * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
     */
    private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

}
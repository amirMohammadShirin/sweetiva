package com.sweet.iva.main.model

import com.sweet.iva.DarkThemeConfig
import com.sweet.iva.ThemeBrand
import com.sweet.iva.core.ui.model.IViewState
import com.sweet.iva.core.ui.navigation.ApplicationRoutes

internal data class MainViewState(
    val loading: Boolean = true,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val themeBrand: ThemeBrand = ThemeBrand.ANDROID,
    val useDynamicColor: Boolean = false,
    val startDestination: String = ApplicationRoutes.introGraphRoute
) : IViewState

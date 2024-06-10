package com.sweet.iva.feature.intro

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.sweet.iva.core.ui.helper.registerDestination
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationAnimation
import com.sweet.iva.feature.intro.view.IntroScreen

/**
 * Created by aShirin on 6/8/2024.
 */

val introScreen = IntroScreen()

fun NavGraphBuilder.introGraph() {

    navigation(
        route = ApplicationRoutes.introGraphRoute,
        startDestination = ApplicationRoutes.introScreenRoute
    ) {

        registerDestination(introScreen)

    }

}
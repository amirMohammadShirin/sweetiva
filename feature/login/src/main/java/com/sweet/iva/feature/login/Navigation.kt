package com.sweet.iva.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.sweet.iva.core.ui.helper.registerDestination
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationAnimation
import com.sweet.iva.feature.login.phoneEntry.view.PhoneEntryScreen
import com.sweet.iva.feature.login.verification.view.VerificationScreen

/**
 * Created by aShirin on 6/9/2024.
 */

val phoneEntryScreen = PhoneEntryScreen()
val verificationScreen = VerificationScreen()


fun NavGraphBuilder.loginGraph() {

    navigation(
        startDestination = ApplicationRoutes.phoneEntryScreenRoute,
        route = ApplicationRoutes.loginGraphRoute
    ) {
        registerDestination(
            phoneEntryScreen
        )
        registerDestination(
            verificationScreen
        )
    }


}
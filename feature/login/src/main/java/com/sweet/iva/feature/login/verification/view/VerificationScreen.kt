package com.sweet.iva.feature.login.verification.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.login.verification.model.VerificationAction
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import com.sweet.iva.feature.login.verification.viewmodel.VerificationViewModel

class VerificationScreen :
    BaseScreen<VerificationUiModel, VerificationAction, VerificationEvent>(
        route = ApplicationRoutes.loginVerificationScreenRoute,
        name = ""
    ) {
    @Composable
    override fun viewModel(): VerificationViewModel = hiltViewModel()

    @Composable
    override fun Content(state: VerificationUiModel) {

        Column {
            Text(text = parameters[NavigationParam.TRACKING_CODE] ?: "hech")
        }

    }
}
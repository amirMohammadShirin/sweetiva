package com.sweet.iva.feature.login.verification.model

import com.sweet.iva.core.ui.model.IAction

sealed interface VerificationAction : IAction {
    data class StoreInitialData(
        val phoneNumber: String,
        val trackingCode: String,
        val otpTime: String
    ) : VerificationAction

    data class VerificationCodeChanged(val verificationCode: String) : VerificationAction
    data object Confirm : VerificationAction
    data object ResendVerificationCode : VerificationAction

}
package com.sweet.iva.feature.login.verification.model

import com.sweet.iva.core.ui.model.IAction

sealed interface VerificationAction : IAction {

    data class StoreTrackingCode(val trackingCode: String) : VerificationAction
    data class StorePhoneNumber(val phoneNumber: String) : VerificationAction
    data class VerificationCodeChanged(val verificationCode: String) : VerificationAction

}
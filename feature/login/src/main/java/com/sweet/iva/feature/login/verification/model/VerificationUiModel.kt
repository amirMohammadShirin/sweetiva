package com.sweet.iva.feature.login.verification.model

data class VerificationUiModel(
    val trackingCode: String = "",
    val timer: TimerUiModel = TimerUiModel(),
    val verificationCode: VerificationCodeUiModel = VerificationCodeUiModel(),
)

data class VerificationCodeUiModel(
    val value: String = "12",
    var length: Int = 4
)

data class TimerUiModel(
    val value: String = "",
    val finished: Boolean = false
)

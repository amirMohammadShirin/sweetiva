package com.sweet.iva.feature.login.verification.model

data class VerificationUiModel(
    val phoneNumber: String = "",
    val timer: TimerUiModel = TimerUiModel(),
    val verificationCode: VerificationCodeUiModel = VerificationCodeUiModel(),
    val loading: Boolean = false
)

data class VerificationCodeUiModel(
    val value: String = "",
    var length: Int = 4
)

data class TimerUiModel(
    val value: String = "",
    val finished: Boolean = false
)

package com.sweet.iva.feature.login.verification.model

data class VerificationUiModel(
    val trackingCode: String = "",
    val timer: TimerUiModel = TimerUiModel(),
)

data class TimerUiModel(
    val value: String = "",
    val finished: Boolean = false
)

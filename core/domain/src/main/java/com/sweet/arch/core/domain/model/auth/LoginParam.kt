package com.sweet.arch.core.domain.model.auth

data class LoginParam(
    val phoneNumber: String,
    val trackingCode: String,
    val otpValue: String
)
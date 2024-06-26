package com.sweet.arch.core.domain.model.auth

data class AuthTokenParam(
    val phoneNumber: String,
    val trackingCode: String,
    val otpValue: String
)
package com.sweet.iva.core.network.model

data class AuthTokenNetworkParam (
    val phoneNumber: String,
    val trackingCode: String,
    val otpValue: String
)
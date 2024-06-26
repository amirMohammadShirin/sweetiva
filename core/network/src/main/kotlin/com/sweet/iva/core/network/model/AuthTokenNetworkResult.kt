package com.sweet.iva.core.network.model

data class AuthTokenNetworkResult(
    val accessToken: String,
    val refreshToken: String,
)

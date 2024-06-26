package com.sweet.arch.core.domain.model.auth

data class AuthTokenResult(
    val accessToken: String,
    val refreshToken: String,
)

package com.sweet.arch.core.domain.model.auth

data class LoginResult(
    val accessToken: String,
    val refreshToken: String,
)

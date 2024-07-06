package com.sweet.iva.core.designsystem.component.model

/**
 * Created by aShirin on 7/6/2024.
 */
data class UserAccountUiModel(
    val id: String,
    val name: String,
    val accountNumber: String,
    val iban: String,
    val balance: String? = null,
)
package com.sweet.iva.feature.home.useraccount.model

data class UserAccountUiModel(
    val accounts: List<Account> = listOf(),
)

data class Account(
    val id: String,
    val name: String,
    val accountNumber: String,
    val iban: String,
    val balance: String? = null,
)
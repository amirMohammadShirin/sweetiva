package com.sweet.iva.feature.login.phoneEntry.model

data class PhoneEntryUiModel(
    val phoneNumberModel: PhoneNumberModel = PhoneNumberModel()
)

data class PhoneNumberModel(
    val value: String = "",
    val errorMessage: String? = null
)

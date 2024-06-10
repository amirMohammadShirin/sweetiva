package com.sweet.iva.feature.login.phoneEntry.model

data class PhoneEntryUiModel(
    val phoneNumberModel: PhoneNumberModel = PhoneNumberModel(),
) {
    fun isLoginEnable(): Boolean {
        return phoneNumberModel.errorMessage.isNullOrEmpty()
    }
}


data class PhoneNumberModel(
    val value: String = "",
    val errorMessage: String? = null
)

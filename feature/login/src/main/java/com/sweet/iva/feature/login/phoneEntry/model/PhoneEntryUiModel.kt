package com.sweet.iva.feature.login.phoneEntry.model

data class PhoneEntryUiModel(
    val loading: Boolean = false,
    val phoneNumberModel: PhoneNumberModel = PhoneNumberModel(),
) {
    fun isLoginEnable(): Boolean {
        return phoneNumberModel.errorMessage.isNullOrEmpty() &&
                phoneNumberModel.value.isNotEmpty()

    }
}


data class PhoneNumberModel(
    val value: String = "",
    val errorMessage: String? = null
)

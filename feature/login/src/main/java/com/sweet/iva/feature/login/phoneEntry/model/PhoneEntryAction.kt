package com.sweet.iva.feature.login.phoneEntry.model

import com.sweet.iva.core.ui.model.Action

/**
 * Created by aShirin on 6/9/2024.
 */
sealed interface PhoneEntryAction : Action {
    data object OnConfirmClicked : PhoneEntryAction
    data class OnPhoneNumberChanged(val phoneNumber: String) : PhoneEntryAction
}
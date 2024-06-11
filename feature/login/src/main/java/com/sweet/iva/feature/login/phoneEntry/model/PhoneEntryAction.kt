package com.sweet.iva.feature.login.phoneEntry.model

import com.sweet.iva.core.ui.model.IAction

/**
 * Created by aShirin on 6/9/2024.
 */
sealed interface PhoneEntryAction : IAction {
    data object OnConfirmClicked : PhoneEntryAction
    data class OnPhoneNumberChanged(val phoneNumber: String) : PhoneEntryAction
}
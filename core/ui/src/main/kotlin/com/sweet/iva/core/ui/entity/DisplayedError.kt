package com.sweet.iva.core.ui.entity

sealed class DisplayedError(val displayedMessage: String) {

    class SnackBarError(
        message: String
    ) : DisplayedError(displayedMessage = message)

    class ToastError(message: String) : DisplayedError(displayedMessage = message)

}
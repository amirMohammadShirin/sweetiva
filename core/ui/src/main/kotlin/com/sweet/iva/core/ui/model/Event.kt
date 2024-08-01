package com.sweet.iva.core.ui.model


/**
 * Created by aShirin on 12/27/2023.
 */
interface Event {
    data class ShowSnack(val message: String) : Event
    data class ShowToast(val message: String) : Event
}
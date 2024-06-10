package com.sweet.iva.core.ui.model

import com.sweet.iva.core.ui.navigation.NavigationCommand


/**
 * Created by aShirin on 12/27/2023.
 */
interface IEvent {
    data class Navigate(val command: NavigationCommand) : IEvent
    data class ShowSnack(val message: String) : IEvent
    data class ShowToast(val message: String) : IEvent
}
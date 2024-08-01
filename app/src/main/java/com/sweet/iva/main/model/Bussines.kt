package com.sweet.iva.main.model

import com.sweet.iva.core.ui.model.IAction
import com.sweet.iva.core.ui.model.IEvent


internal sealed interface MainAction : IAction {
    data object FetchStartUpData : MainAction
}

internal sealed interface MainEvent : IEvent
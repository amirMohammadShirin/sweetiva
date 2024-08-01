package com.sweet.iva.main.model

import com.sweet.iva.core.ui.model.Action
import com.sweet.iva.core.ui.model.Event


internal sealed interface MainAction : Action {
    data object FetchStartUpData : MainAction
}

internal sealed interface MainEvent : Event
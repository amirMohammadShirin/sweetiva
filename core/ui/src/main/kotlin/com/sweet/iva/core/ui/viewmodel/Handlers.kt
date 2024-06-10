package com.sweet.iva.core.ui.viewmodel

interface ActionHandler<in T> {
    fun handle(action: T)

}

interface ViewModelActionHandler<in T> : ActionHandler<T> {

    val actionHandlers: Set<ActionHandler<T>>
    override fun handle(action: T) {
        actionHandlers.onEach {
            try {
                it.handle(action)
            } catch (_: Exception) {
            }
        }
    }

}

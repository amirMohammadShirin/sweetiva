package com.sweet.iva.core.ui.model

/**
 * Created by aShirin on 12/27/2023.
 */
fun interface IReducer<Mutation, ViewState> {
    fun invoke(mutation: Mutation, currentState: ViewState): ViewState
}
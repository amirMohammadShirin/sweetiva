package com.sweet.iva.feature.login.phoneEntry.viewmodel

import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryAction
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryEvent
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by aShirin on 6/9/2024.
 */
@HiltViewModel
class PhoneEntryViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<PhoneEntryUiModel, PhoneEntryAction, PhoneEntryEvent>(
    initialState = PhoneEntryUiModel(),
    ioDispatcher = dispatcherProvider.io,
    defaultDispatcher = dispatcherProvider.default,
    mainDispatcher = dispatcherProvider.ui
) {
    override fun handleAction(action: PhoneEntryAction) {

    }

}
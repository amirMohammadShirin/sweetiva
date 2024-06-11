package com.sweet.iva.feature.login.phoneEntry.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.user.GetLoginTokenUseCase
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.common.util.ValidationState
import com.sweet.iva.core.common.util.ValidationUtil
import com.sweet.iva.core.ui.model.IEvent
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryAction
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryEvent
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by aShirin on 6/9/2024.
 */
@HiltViewModel
class PhoneEntryViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val loginTokenUseCase: GetLoginTokenUseCase
) : BaseViewModel<PhoneEntryUiModel, PhoneEntryAction, PhoneEntryEvent>(
    initialState = PhoneEntryUiModel()
) {
    override fun handleAction(action: PhoneEntryAction) {
        when (action) {
            is PhoneEntryAction.OnPhoneNumberChanged -> {
                changePhoneNumber(action.phoneNumber)
            }

            PhoneEntryAction.OnConfirmClicked -> {
                sendOtp()
            }
        }
    }

    private fun sendOtp() {
        viewModelScope.launch(dispatcherProvider.io) {
            val result = loginTokenUseCase.execute(null)
            sendEvent(IEvent.ShowSnack(result))
            updateState {
                it.copy(
                    loading = true
                )
            }
            delay(5000)
            updateState {
                it.copy(
                    loading = false
                )
            }
        }
    }

    private fun changePhoneNumber(phoneNumber: String) {

        val errorMessage =
            if (ValidationUtil.phoneNumber(phoneNumber) == ValidationState.INVALID) "شماره تلفن همراه معتبر نمی‌باشد" else null

        updateState {
            it.copy(
                phoneNumberModel = it.phoneNumberModel.copy(
                    value = phoneNumber,
                    errorMessage = errorMessage
                )
            )
        }
    }

}
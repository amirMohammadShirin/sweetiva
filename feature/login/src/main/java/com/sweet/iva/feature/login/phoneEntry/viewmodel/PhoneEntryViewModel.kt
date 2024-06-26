package com.sweet.iva.feature.login.phoneEntry.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.model.auth.LoginOtpParam
import com.sweet.arch.core.domain.usecase.auth.SendLoginOtpUseCase
import com.sweet.iva.core.common.model.DisplayException
import com.sweet.iva.core.common.util.ValidationState
import com.sweet.iva.core.common.util.ValidationUtil
import com.sweet.iva.core.ui.model.IEvent
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationCommand
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryAction
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryEvent
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by aShirin on 6/9/2024.
 */
@HiltViewModel
class PhoneEntryViewModel @Inject constructor(
    private val sendLoginOtpUseCase: SendLoginOtpUseCase
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
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->

                updateState {
                    it.copy(loading = false)
                }

                if (throwable is DisplayException) {
                    sendEvent(IEvent.ShowSnack(throwable.message ?: "خطا در دریافت اطلاعات"))
                    return@CoroutineExceptionHandler
                }

                sendEvent(IEvent.ShowSnack("خطا در دریافت اطلاعات"))

            }
        ) {

            updateState {
                it.copy(
                    loading = true
                )
            }

            val result =
                sendLoginOtpUseCase.execute(LoginOtpParam(currentState.phoneNumberModel.value))

            updateState {
                it.copy(
                    loading = false
                )
            }

            navigateTo(
                NavigationCommand.ToWithData(
                    ApplicationRoutes.loginVerificationScreenRoute,
                    linkedMapOf(
                        NavigationParam.TRACKING_CODE to result.trackingCode,
                        NavigationParam.PHONE_NUMBER to currentState.phoneNumberModel.value,
                        NavigationParam.OTP_TIME to result.otpTime.toString()
                    )
                )
            )

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
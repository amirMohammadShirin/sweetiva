package com.sweet.iva.feature.login.phoneEntry.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.auth.LoginUseCase
import com.sweet.arch.core.domain.usecase.auth.SendLoginOtpUseCase
import com.sweet.iva.core.common.model.DisplayException
import com.sweet.iva.core.common.util.ValidationState
import com.sweet.iva.core.common.util.ValidationUtil
import com.sweet.iva.core.ui.model.Event
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationCommand
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
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
) : BaseViewModel<PhoneEntryUiModel, PhoneEntryEvent>(
    initialState = PhoneEntryUiModel()
) {

    fun sendOtp() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->

                updateState {
                    it.copy(loading = false)
                }

                if (throwable is DisplayException) {
                    sendEvent(Event.ShowSnack(throwable.message ?: "خطا در دریافت اطلاعات"))
                    return@CoroutineExceptionHandler
                }

                sendEvent(Event.ShowSnack("خطا در دریافت اطلاعات"))

            }
        ) {

            updateState {
                it.copy(
                    loading = true
                )
            }

            val result =
                sendLoginOtpUseCase.execute(
                    SendLoginOtpUseCase.Companion.Param(currentState.phoneNumberModel.value)
                )

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

    fun changePhoneNumber(phoneNumber: String) {

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
package com.sweet.iva.feature.login.verification.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.model.auth.LoginParam
import com.sweet.arch.core.domain.usecase.auth.LoginUseCase
import com.sweet.arch.core.domain.usecase.user.GetCurrentUserUseCase
import com.sweet.iva.core.common.util.TimeUtil
import com.sweet.iva.core.ui.model.IEvent
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.verification.model.VerificationAction
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) :
    BaseViewModel<VerificationUiModel, VerificationAction, VerificationEvent>(
        initialState = VerificationUiModel()
    ) {

    private var phoneNumber = ""
    private var trackingCode = ""
    private val timerInterval: Long = 1000
    private var timerValue: Long = 20000

    override fun handleAction(action: VerificationAction) {
        when (action) {
            is VerificationAction.VerificationCodeChanged -> changeVerificationCode(action.verificationCode)
            is VerificationAction.Confirm -> confirm()
            is VerificationAction.ResendVerificationCode -> navigateBack()
            is VerificationAction.StoreInitialData -> {
                start(action)
            }
        }
    }

    private fun start(action: VerificationAction.StoreInitialData) {
        run {
            savePhoneNumber(action.phoneNumber)
            saveTrackingCode(action.trackingCode)
            saveOtpTime(action.otpTime)
        }.also {
            updateState {
                it.copy(
                    phoneNumber = phoneNumber
                )
            }
            startTimer()
        }
    }

    private fun saveOtpTime(time: String) {
        try {
            timerValue = time.toLong()
        } catch (_: Exception) {
        }
    }

    private fun confirm() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                updateState {
                    it.copy(
                        loading = false
                    )
                }
                sendEvent(IEvent.ShowSnack(throwable.message ?: " "))
            }
        ) {
            updateState {
                it.copy(
                    loading = true
                )
            }
            val result = loginUseCase.execute(
                LoginParam(
                    trackingCode = trackingCode,
                    phoneNumber = currentState.phoneNumber,
                    otpValue = currentState.verificationCode.value
                )
            )

            updateState {
                it.copy(
                    loading = false
                )
            }

            if (result) {
                val currentUser = getCurrentUserUseCase.execute(null)
                currentUser?.let {
                    sendEvent(IEvent.ShowSnack("${it.phoneNumber.value} -> ${it.identity?.accessToken}"))
                }
            }

        }
    }

    private fun changeVerificationCode(verificationCode: String) {
        updateState {
            it.copy(
                verificationCode = it.verificationCode.copy(
                    value = verificationCode
                )
            )
        }
    }

    private fun saveTrackingCode(value: String) {
        trackingCode = value
    }

    private fun savePhoneNumber(value: String) {
        phoneNumber = value
    }

    private fun startTimer() {

        object : CountDownTimer(timerValue, timerInterval) {

            override fun onTick(p0: Long) {
                updateState {
                    it.copy(
                        timer = it.timer.copy(
                            value = TimeUtil.toDualTimeFormat(p0),
                            finished = false
                        )
                    )
                }
            }

            override fun onFinish() {
                updateState {
                    it.copy(
                        timer = it.timer.copy(
                            value = "00:00",
                            finished = true
                        )
                    )
                }
            }
        }.start()

    }

}
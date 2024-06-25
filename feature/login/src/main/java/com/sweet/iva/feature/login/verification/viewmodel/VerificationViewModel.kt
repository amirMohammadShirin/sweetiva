package com.sweet.iva.feature.login.verification.viewmodel

import android.os.CountDownTimer
import com.sweet.iva.core.common.util.TimeUtil
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.verification.model.VerificationAction
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor() :
    BaseViewModel<VerificationUiModel, VerificationAction, VerificationEvent>(
        initialState = VerificationUiModel()
    ) {

    private var phoneNumber = ""
    private var trackingCode = ""
    private val timerInterval: Long = 1000
    private var timerValue: Long = 10000

    override fun handleAction(action: VerificationAction) {
        when (action) {
            is VerificationAction.StorePhoneNumber -> savePhoneNumber(action.phoneNumber)
            is VerificationAction.StoreTrackingCode -> {
                saveTrackingCode(action.trackingCode)
                startTimer()
            }

            is VerificationAction.VerificationCodeChanged -> changeVerificationCode(action.verificationCode)
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
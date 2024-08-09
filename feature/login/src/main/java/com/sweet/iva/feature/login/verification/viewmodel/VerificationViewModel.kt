package com.sweet.iva.feature.login.verification.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.viewModelScope
import com.sweet.arch.core.domain.usecase.auth.LoginUseCase
import com.sweet.iva.core.common.util.TimeUtil
import com.sweet.iva.core.ui.model.Event
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<VerificationUiModel, VerificationEvent>(
    initialState = VerificationUiModel(),
) {
    private var phoneNumber = ""
    private var trackingCode = ""
    private val timerInterval: Long = 1000
    private var timerValue: Long = 20000

    fun start(
        phoneNumber: String,
        trackingCode: String,
        otpTime: String
    ) {
        run {
            savePhoneNumber(phoneNumber)
            saveTrackingCode(trackingCode)
            saveOtpTime(otpTime)
        }.also {
            updateState {
                it.copy(
                    phoneNumber = phoneNumber,
                )
            }
            startTimer()
        }
    }

    fun saveOtpTime(time: String) {
        try {
            timerValue = time.toLong()
        } catch (_: Exception) {
        }
    }

    fun confirm() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                updateState {
                    it.copy(
                        loading = false,
                    )
                }
                sendEvent(Event.ShowSnack(throwable.message ?: " "))
            },
        ) {
            updateState {
                it.copy(
                    loading = true,
                )
            }

            loginUseCase.execute(
                LoginUseCase.Companion.Param(
                    trackingCode = trackingCode,
                    phoneNumber = currentState.phoneNumber,
                    otpValue = currentState.verificationCode.value,
                ),
            )

            updateState {
                it.copy(
                    loading = false,
                )
            }
        }
    }

    fun changeVerificationCode(verificationCode: String) {
        updateState {
            it.copy(
                verificationCode =
                it.verificationCode.copy(
                    value = verificationCode,
                ),
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
                        timer =
                        it.timer.copy(
                            value = TimeUtil.toDualTimeFormat(p0),
                            finished = false,
                        ),
                    )
                }
            }

            override fun onFinish() {
                updateState {
                    it.copy(
                        timer =
                        it.timer.copy(
                            value = "00:00",
                            finished = true,
                        ),
                    )
                }
            }
        }.start()
    }
}

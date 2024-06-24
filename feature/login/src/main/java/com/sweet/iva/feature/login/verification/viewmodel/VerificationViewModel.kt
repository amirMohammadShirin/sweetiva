package com.sweet.iva.feature.login.verification.viewmodel

import android.os.CountDownTimer
import com.sweet.iva.core.common.util.TimeUtil
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.login.verification.model.VerificationAction
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor() :
    BaseViewModel<VerificationUiModel, VerificationAction, VerificationEvent>(
        initialState = VerificationUiModel()
    ) {

    override fun handleAction(action: VerificationAction) {

    }

    fun startTimer(untilInMillis: Long, intervalInMillis: Long) {

        object : CountDownTimer(untilInMillis, intervalInMillis) {

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
package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.user.PhoneNumber
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

/**
 * Created by aShirin on 6/12/2024.
 */
class SendLoginOtpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) :
    BaseUseCase<SendLoginOtpUseCase.Companion.Param, LoginOTPResult>() {

    companion object {
        data class Param(val phoneNumber: String)
    }

    override suspend fun onExecute(param: Param): LoginOTPResult {
        return authenticationRepository.sendLoginOtp(PhoneNumber(param.phoneNumber))
    }

}
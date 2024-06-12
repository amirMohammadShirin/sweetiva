package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginOtpParam
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

/**
 * Created by aShirin on 6/12/2024.
 */
class SendLoginOtpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) :
    BaseUseCase<LoginOtpParam, LoginOTPResult>() {

    override suspend fun onExecute(param: LoginOtpParam): LoginOTPResult {
        return authenticationRepository.sendLoginOtp(param)
    }

}
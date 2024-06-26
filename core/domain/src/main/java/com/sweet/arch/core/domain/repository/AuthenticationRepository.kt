package com.sweet.arch.core.domain.repository

import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginOtpParam

/**
 * Created by aShirin on 6/12/2024.
 */
interface AuthenticationRepository {
    suspend fun sendLoginOtp(param: LoginOtpParam):LoginOTPResult

}
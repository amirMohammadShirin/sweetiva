package com.sweet.arch.core.domain.repository

import com.sweet.arch.core.domain.model.auth.LoginOTP
import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginResult
import com.sweet.arch.core.domain.model.auth.LoginTrackingCode
import com.sweet.arch.core.domain.model.user.PhoneNumber
import com.sweet.arch.core.domain.model.user.User

/**
 * Created by aShirin on 6/12/2024.
 */
interface AuthenticationRepository {
    suspend fun sendLoginOtp(phoneNumber: PhoneNumber): LoginOTPResult
    suspend fun login(phoneNumber: PhoneNumber, trackingCode: LoginTrackingCode, otp: LoginOTP): LoginResult

}
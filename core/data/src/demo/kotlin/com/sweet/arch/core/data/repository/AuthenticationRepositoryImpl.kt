package com.sweet.arch.core.data.repository

import com.sweet.arch.core.domain.model.auth.LoginOTP
import com.sweet.arch.core.domain.model.auth.LoginResult
import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginTrackingCode
import com.sweet.arch.core.domain.model.user.PhoneNumber
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.network.datasource.AuthenticationRemoteDataSource
import com.sweet.iva.core.network.model.AuthTokenNetworkParam
import com.sweet.iva.core.network.model.AuthTokenNetworkResult
import com.sweet.iva.core.network.model.LoginOtpNetworkParam
import com.sweet.iva.core.network.model.LoginOtpNetworkResult
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by aShirin on 6/12/2024.
 */
class AuthenticationRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {

    override suspend fun sendLoginOtp(phoneNumber: PhoneNumber): LoginOTPResult {
        return withContext(dispatcherProvider.io) {
            remoteDataSource.sendLoginOtp(phoneNumber.toNetworkModel())
                .toDomainModel()
        }
    }

    override suspend fun login(
        phoneNumber: PhoneNumber,
        trackingCode: LoginTrackingCode,
        otp: LoginOTP
    ): LoginResult {
        return withContext(dispatcherProvider.io) {
            remoteDataSource.getAuthToken(
                AuthTokenNetworkParam(
                    phoneNumber = phoneNumber.value,
                    otpValue = otp.value,
                    trackingCode = trackingCode.value
                )
            ).toDomainModel()
        }
    }

}

private fun PhoneNumber.toNetworkModel(): LoginOtpNetworkParam = LoginOtpNetworkParam(
    this.value
)

private fun AuthTokenNetworkResult.toDomainModel() = LoginResult(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)


private fun LoginOtpNetworkResult.toDomainModel(): LoginOTPResult {
    return LoginOTPResult(
        trackingCode = this.trackingCode,
        otpTime = this.otpTime
    )
}



package com.sweet.arch.core.data.repository

import com.sweet.arch.core.domain.model.auth.LoginParam
import com.sweet.arch.core.domain.model.auth.LoginResult
import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginOtpParam
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

    override suspend fun sendLoginOtp(param: LoginOtpParam): LoginOTPResult {
        return withContext(dispatcherProvider.io) {
            return@withContext remoteDataSource.sendLoginOtp(param.toNetworkModel()).toDomainModel()
        }
    }

    override suspend fun login(param: LoginParam): LoginResult {
        return withContext(dispatcherProvider.io) {
            return@withContext remoteDataSource.getAuthToken(param.toNetworkModel()).toDomainModel()
        }
    }

}

private fun AuthTokenNetworkResult.toDomainModel() = LoginResult(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)

private fun LoginParam.toNetworkModel() = AuthTokenNetworkParam(
    phoneNumber = this.phoneNumber,
    trackingCode = this.trackingCode,
    otpValue = this.otpValue
)

private fun LoginOtpNetworkResult.toDomainModel(): LoginOTPResult {
    return LoginOTPResult(
        trackingCode = this.trackingCode,
        otpTime = this.otpTime
    )
}

private fun LoginOtpParam.toNetworkModel(): LoginOtpNetworkParam {
    return LoginOtpNetworkParam(
        phoneNumber = this.phoneNumber
    )
}

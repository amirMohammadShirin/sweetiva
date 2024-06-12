package com.sweet.arch.core.data.repository

import com.sweet.arch.core.domain.model.auth.LoginOTPResult
import com.sweet.arch.core.domain.model.auth.LoginOtpParam
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.network.datasource.AuthenticationRemoteDataSource
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

}

private fun LoginOtpNetworkResult.toDomainModel(): LoginOTPResult {
    return LoginOTPResult(
        trackingCode = this.trackingCode
    )
}

private fun LoginOtpParam.toNetworkModel(): LoginOtpNetworkParam {
    return LoginOtpNetworkParam(
        phoneNumber = this.phoneNumber
    )
}

package com.sweet.iva.core.network.service.auth

import com.sweet.iva.core.network.helper.Response
import com.sweet.iva.core.network.model.LoginOtpNetworkParam
import com.sweet.iva.core.network.model.LoginOtpNetworkResult
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created by aShirin on 6/12/2024.
 */
interface AuthenticationApi {
    @GET("/v1/authentication/token")
    suspend fun sendLoginOtp(@Body body: LoginOtpNetworkParam) : Response<LoginOtpNetworkResult>

}
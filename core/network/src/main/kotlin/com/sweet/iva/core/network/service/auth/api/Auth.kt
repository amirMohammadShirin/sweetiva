package com.sweet.iva.core.network.service.auth.api

import com.sweet.iva.core.network.helper.Response
import com.sweet.iva.core.network.service.auth.model.LoginOtpParam
import com.sweet.iva.core.network.service.auth.model.LoginOtpResult
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created by aShirin on 6/12/2024.
 */
interface Auth {
    @GET("/v1/authentication/token")
    suspend fun sendLoginOtp(@Body body: LoginOtpParam) : Response<LoginOtpResult>

}
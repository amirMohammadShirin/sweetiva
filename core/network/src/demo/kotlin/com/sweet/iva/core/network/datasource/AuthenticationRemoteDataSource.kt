package com.sweet.iva.core.network.datasource

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sweet.iva.core.network.helper.Response
import com.sweet.iva.core.network.helper.parseResponse
import com.sweet.iva.core.network.model.LoginOtpNetworkParam
import com.sweet.iva.core.network.model.LoginOtpNetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by aShirin on 6/12/2024.
 */

class AuthenticationRemoteDataSource @Inject constructor() {

    private val gson = Gson()

    suspend fun sendLoginOtp(
        params: LoginOtpNetworkParam
    ): LoginOtpNetworkResult {
        return withContext(Dispatchers.Default) {
            delay(1000)
            val response = gson.fromJson(
                mockLoginOtpResponse,
                object : TypeToken<Response<LoginOtpNetworkResult>>() {}
            )
            return@withContext response.parseResponse()
        }

    }

}

const val mockLoginOtpResponse = "{\n" +
        "  \"data\": {\n" +
        "    \"trackingCode\": \"0021477191\"\n" +
        "  }\n" +
        "}\n"

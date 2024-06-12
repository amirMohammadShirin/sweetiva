package com.sweet.iva.core.network.api

import retrofit2.http.GET

/**
 * Created by aShirin on 6/12/2024.
 */
interface Auth {

    @GET("/v1/authentication/token")
    fun sendLoginOtp()

}
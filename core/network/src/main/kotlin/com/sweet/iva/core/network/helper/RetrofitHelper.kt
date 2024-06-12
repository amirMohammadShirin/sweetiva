package com.sweet.iva.core.network.helper

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aShirin on 6/12/2024.
 */
object RetrofitHelper {

    fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit
            .Builder()
            .apply {
                client(okHttpClient)
                baseUrl(baseUrl)
                addConverterFactory(GsonConverterFactory.create(Gson()))
            }
            .build()

}
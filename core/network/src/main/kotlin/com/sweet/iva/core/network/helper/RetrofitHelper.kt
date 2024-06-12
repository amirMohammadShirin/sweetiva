package com.sweet.iva.core.network.helper

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
                addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            }
            .build()

}
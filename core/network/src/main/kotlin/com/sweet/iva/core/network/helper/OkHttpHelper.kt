package com.sweet.iva.core.network.helper

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.TreeMap
import java.util.concurrent.TimeUnit

object OkHttpHelper {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun createClient(
        timeOutInSecond: Long,
        interceptors: Map<Int, Interceptor> = mapOf(),
        headers: Map<String, String> = mapOf()
    ): OkHttpClient = OkHttpClient
        .Builder()
        .apply {

            writeTimeout(timeOutInSecond, TimeUnit.SECONDS)
            readTimeout(timeOutInSecond, TimeUnit.SECONDS)
            connectTimeout(timeOutInSecond, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)

            if (interceptors.isNotEmpty())
                TreeMap(interceptors).forEach {
                    addInterceptor(it.value)
                }

            if (headers.isNotEmpty())
                addInterceptor { chain ->
                    val req = chain.request().newBuilder()
                    headers.forEach {
                        req.addHeader(it.key, it.value)
                    }
                    chain.proceed(req.build())
                }

            addInterceptor(loggingInterceptor)

        }
        .build()


}
package com.sweet.iva.core.network.di

import com.sweet.iva.core.network.helper.OkHttpHelper
import com.sweet.iva.core.network.helper.RetrofitHelper
import com.sweet.iva.core.network.service.auth.AuthenticationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

private const val BASE_URL = "http://baseurl.com"

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        @Provides
        fun provideDefaultRetrofit(): Retrofit {
            return RetrofitHelper.createRetrofit(
                BASE_URL,
                OkHttpHelper.createClient(
                    40
                )
            )
        }

        @Provides
        fun providerAuthenticationApi(
            retrofit: Retrofit
        ): AuthenticationApi {
            return retrofit.create(AuthenticationApi::class.java)
        }

    }
}
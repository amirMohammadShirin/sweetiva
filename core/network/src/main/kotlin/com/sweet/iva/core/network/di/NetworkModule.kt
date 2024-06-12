package com.sweet.iva.core.network.di

import com.sweet.arch.core.domain.datasource.remote.AuthenticationRemoteDataSource
import com.sweet.iva.core.network.datasource.AuthenticationRemoteDataSourceImpl
import com.sweet.iva.core.network.helper.OkHttpHelper
import com.sweet.iva.core.network.helper.RetrofitHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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


    }

    @Binds
    abstract fun bindAAuthenticationRemoteDataSource(authenticationRemoteDataSourceImpl: AuthenticationRemoteDataSourceImpl): AuthenticationRemoteDataSource

}
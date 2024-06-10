package com.sweet.iva.injection

import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.common.dispatcher.StandardDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainModule {

    @Binds
    abstract fun bindStandardDispatcherProvider(standardDispatcherProvider: StandardDispatcherProvider): DispatcherProvider

}
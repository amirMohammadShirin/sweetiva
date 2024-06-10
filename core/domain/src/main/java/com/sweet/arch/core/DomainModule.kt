package com.sweet.arch.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by aShirin on 6/1/2024.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    companion object {

        @Provides
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    }

}
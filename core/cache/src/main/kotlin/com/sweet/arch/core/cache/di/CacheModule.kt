package com.sweet.arch.core.cache.di

import com.sweet.arch.core.cache.SharedPreferencesCache
import com.sweet.arch.core.domain.cache.Cache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindSharedPreferencesCache(sharedPreferencesCache: SharedPreferencesCache):Cache

}
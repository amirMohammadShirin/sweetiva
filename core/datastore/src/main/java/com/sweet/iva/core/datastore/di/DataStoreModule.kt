package com.sweet.iva.core.datastore.di

import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.model.user.User
import com.sweet.iva.core.datastore.UserCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    abstract fun bindDataStore(userCache: UserCache): ReactiveCache<User>

}
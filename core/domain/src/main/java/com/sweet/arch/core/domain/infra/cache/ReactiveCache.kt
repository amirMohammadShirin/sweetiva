package com.sweet.arch.core.domain.infra.cache

import kotlinx.coroutines.flow.Flow


interface ReactiveCache<T> {

    val key: CacheKey
    fun dataStream(): Flow<T>
    suspend fun save(data: T)

}
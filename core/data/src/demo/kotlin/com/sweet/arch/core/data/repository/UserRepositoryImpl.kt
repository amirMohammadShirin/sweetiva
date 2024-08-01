package com.sweet.arch.core.data.repository

import com.google.gson.Gson
import com.sweet.arch.core.domain.infra.cache.Cache
import com.sweet.arch.core.domain.infra.cache.CacheKey
import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    @Named("sharedPreferences")
    private val cache: Cache,
    private val reactiveUserCache: ReactiveCache<User>,
) : UserRepository {
    override fun getCurrentUser(): User? {
        val user = cache.getString(CacheKey.CURRENT_USER)
        return try {
            Gson().fromJson(user, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override fun currentUserStream(): Flow<User> = reactiveUserCache.stream

    override fun upsertCurrentUser(user: User): User {
        cache.saveObject(CacheKey.CURRENT_USER, user)
        return user
    }

    override suspend fun updateCurrentUserOnStream(user: User?) {
       withContext(dispatcherProvider.io){
           reactiveUserCache.save(user)
       }
    }

    override suspend fun removeCurrentUser() {
        cache.saveObject(CacheKey.CURRENT_USER, "")
        reactiveUserCache.clear()
    }
}
package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.infra.cache.Cache
import com.sweet.arch.core.domain.infra.cache.CacheKey
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject
import javax.inject.Named

class UpsertCurrentUserUseCase @Inject constructor(
    @Named("sharedPreferences")
    private val cache: Cache
) : BaseUseCase<User, Boolean>() {
    override suspend fun onExecute(param: User): Boolean {
        return try {
            cache.saveObject(CacheKey.CURRENT_USER, param)
            true
        } catch (e: Exception) {
            false
        }
    }
}
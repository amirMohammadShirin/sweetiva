package com.sweet.arch.core.domain.usecase.user

import com.google.gson.Gson
import com.sweet.arch.core.domain.cache.Cache
import com.sweet.arch.core.domain.enum.CacheKey
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject
import javax.inject.Named

class GetCurrentUserUseCase @Inject constructor(
    @Named("sharedPreferences")
    private val cache: Cache
) : BaseUseCase<Unit?, User?>() {
    override suspend fun onExecute(param: Unit?): User? {
        val user = cache.getObject(CacheKey.CURRENT_USER)
        return try {
            Gson().fromJson(user, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

}
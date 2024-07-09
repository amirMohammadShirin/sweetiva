package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.BaseStreamUseCase
import javax.inject.Inject

/**
 * Created by aShirin on 7/9/2024.
 */
class StreamCurrentUserUseCase @Inject constructor(
    private val reactiveUserCache: ReactiveCache<User>
) : BaseStreamUseCase<Unit?, User>() {
    override fun onStream(param: Unit?) = reactiveUserCache.dataStream()
}
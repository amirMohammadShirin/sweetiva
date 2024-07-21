package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.infra.cache.StreamsData
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

@StreamsData<User>
class StreamCurrentUserUseCase
    @Inject
    constructor(
        private val reactiveCache: ReactiveCache<User>,
    ) : BaseUseCase<User, Boolean>() {
        override suspend fun onExecute(param: User): Boolean {
            reactiveCache.save(param)
            return true
        }
    }

package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.infra.cache.StreamsData
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

@StreamsData<User>
class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : BaseUseCase<Unit?, Boolean>() {
    override suspend fun onExecute(param: Unit?): Boolean {
        userRepository.removeCurrentUser()
        return true
    }
}
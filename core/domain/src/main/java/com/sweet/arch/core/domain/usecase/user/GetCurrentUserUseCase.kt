package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit?, User?>() {
    override suspend fun onExecute(param: Unit?): User? {
        return userRepository.getCurrentUser()
    }

}
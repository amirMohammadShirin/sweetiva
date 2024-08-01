package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.arch.core.domain.usecase.BaseStreamUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by aShirin on 7/9/2024.
 */
class ListenToCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseStreamUseCase<Unit?, User>() {
    override fun onStream(param: Unit?): Flow<User> = userRepository.currentUserStream()
}

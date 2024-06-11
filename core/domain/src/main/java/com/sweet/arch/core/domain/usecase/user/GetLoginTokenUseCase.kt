package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.iva.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by aShirin on 6/11/2024.
 */
class GetLoginTokenUseCase @Inject constructor(
    override val dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : BaseUseCase<Unit?, String>() {

    override suspend fun onExecute(param: Unit?): String {
        return userRepository.test()
    }

}
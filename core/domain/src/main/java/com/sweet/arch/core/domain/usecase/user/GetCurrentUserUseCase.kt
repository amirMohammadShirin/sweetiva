package com.sweet.arch.core.domain.usecase.user

import com.sweet.arch.core.domain.model.user.User
import com.sweet.iva.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by aShirin on 6/11/2024.
 */
@Singleton
class GetCurrentUserUseCase @Inject constructor(
    override val dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit?, User>() {

    override suspend fun onExecute(param: Unit?): User {
        return User.create(
            User.Companion.Argument(
                "09210425101",
                "امیر محمد",
                "شیرین",
                "0021477191"
            )
        )
    }
}
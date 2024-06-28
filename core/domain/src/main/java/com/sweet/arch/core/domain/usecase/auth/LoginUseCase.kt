package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.model.auth.LoginParam
import com.sweet.arch.core.domain.model.user.Identity
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import com.sweet.arch.core.domain.usecase.user.UpsertCurrentUserUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
    private val upsertCurrentUserUseCase: UpsertCurrentUserUseCase,
) : BaseUseCase<LoginParam, Boolean>() {
    override suspend fun onExecute(param: LoginParam): Boolean {
        val authenticationData = repository.login(param)
        val user = User.create(
            User.Companion.Argument(
                phoneNumber = param.phoneNumber,
                identity = if (authenticationData.accessToken.isNotEmpty() && authenticationData.refreshToken.isNotEmpty()) {
                    Identity.Companion.Argument(
                        refreshToken = authenticationData.refreshToken,
                        accessToken = authenticationData.accessToken
                    )
                } else null
            )
        )
        return upsertCurrentUserUseCase.execute(user)
    }
}
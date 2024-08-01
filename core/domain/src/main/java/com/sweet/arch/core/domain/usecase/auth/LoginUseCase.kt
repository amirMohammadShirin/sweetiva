package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.infra.cache.StreamsData
import com.sweet.arch.core.domain.model.auth.LoginOTP
import com.sweet.arch.core.domain.model.auth.LoginTrackingCode
import com.sweet.arch.core.domain.model.user.Identity
import com.sweet.arch.core.domain.model.user.PhoneNumber
import com.sweet.arch.core.domain.model.user.User
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import com.sweet.arch.core.domain.usecase.user.StreamCurrentUserUseCase
import com.sweet.arch.core.domain.usecase.user.UpsertCurrentUserUseCase
import javax.inject.Inject

@StreamsData<User>
class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
    private val upsertCurrentUserUseCase: UpsertCurrentUserUseCase,
    private val streamCurrentUserUseCase: StreamCurrentUserUseCase,
) : BaseUseCase<LoginUseCase.Companion.Param, User>() {

    companion object {
        data class Param(
            val phoneNumber: String,
            val trackingCode: String,
            val otpValue: String
        )
    }

    override suspend fun onExecute(param: Param): User {
        val authenticationData = repository.login(
            PhoneNumber(param.phoneNumber),
            LoginTrackingCode(param.trackingCode),
            LoginOTP(param.otpValue)
        )
        val user =
            User.create(
                User.Companion.Argument(
                    phoneNumber = param.phoneNumber,
                    identity =
                    if (authenticationData.accessToken.isNotEmpty() && authenticationData.refreshToken.isNotEmpty()) {
                        Identity.Companion.Argument(
                            refreshToken = authenticationData.refreshToken,
                            accessToken = authenticationData.accessToken,
                        )
                    } else {
                        null
                    },
                ),
            )
        upsertCurrentUserUseCase.execute(user)
        streamCurrentUserUseCase.execute(user)
        return user
    }
}

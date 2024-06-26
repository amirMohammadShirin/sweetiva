package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.model.auth.AuthTokenParam
import com.sweet.arch.core.domain.repository.AuthenticationRepository
import com.sweet.arch.core.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetAuthTokenUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) : BaseUseCase<AuthTokenParam, Boolean>() {
    override suspend fun onExecute(param: AuthTokenParam): Boolean {
        repository.getAuthToken(param)
        return true
    }
}
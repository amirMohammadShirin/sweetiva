package com.sweet.arch.core.domain.usecase.auth

import com.sweet.arch.core.domain.usecase.BaseUseCase
import com.sweet.arch.core.domain.usecase.user.GetCurrentUserUseCase
import com.sweet.arch.core.domain.usecase.user.UpsertCurrentUserUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val upsertCurrentUserUseCase: UpsertCurrentUserUseCase
) : BaseUseCase<Unit?, Boolean>() {
    override suspend fun onExecute(param: Unit?): Boolean {
        val currentUserUseCase = getCurrentUserUseCase.execute(null) ?: return false
        return upsertCurrentUserUseCase.execute(currentUserUseCase.logOut())
    }
}
package com.sweet.arch.core.domain.usecase

abstract class BaseUseCase<in PARAM, out RESULT> {
    protected abstract suspend fun onExecute(param: PARAM): RESULT
    suspend fun execute(param: PARAM) = onExecute(param)

}
package com.sweet.iva.core.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in PARAM, out RESULT> {
    protected abstract suspend fun onExecute(param: PARAM): RESULT

    protected abstract val dispatcher: CoroutineDispatcher

    suspend fun execute(param: PARAM) =
        withContext(dispatcher) {
            return@withContext onExecute(param)
        }

}
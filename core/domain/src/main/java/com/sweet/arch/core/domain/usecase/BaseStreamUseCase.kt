package com.sweet.arch.core.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Created by aShirin on 7/9/2024.
 */
abstract class BaseStreamUseCase<PARAM, RESULT> {

    protected abstract fun onStream(param: PARAM): Flow<RESULT>
    fun start(param: PARAM): Flow<RESULT> = onStream(param)

}
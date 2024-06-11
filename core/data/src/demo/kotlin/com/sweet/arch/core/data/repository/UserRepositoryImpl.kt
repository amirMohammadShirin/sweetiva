package com.sweet.arch.core.data.repository

import com.sweet.arch.core.domain.repository.UserRepository
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by aShirin on 6/11/2024.
 */
class UserRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : UserRepository{

    override suspend fun test(): String {
        return withContext(dispatcherProvider.io){
            "i am demo"
        }
    }
}
package com.sweet.arch.core.domain.repository

import com.sweet.arch.core.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): User?
    fun upsertCurrentUser(user: User): User?
    fun currentUserStream():Flow<User>
    suspend fun updateCurrentUserOnStream(user: User?)
    suspend fun removeCurrentUser()
}
package com.sweet.iva.core.datastore

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sweet.arch.core.domain.infra.cache.CacheKey
import com.sweet.arch.core.domain.infra.cache.ReactiveCache
import com.sweet.arch.core.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCache @Inject constructor(
    private val appDataStore: AppDataStore
) : ReactiveCache<User> {

    override val key = CacheKey.CURRENT_USER

    private val gson = Gson()
    override fun dataStream(): Flow<User> {
        return appDataStore.datastore.data.map {
            gson.fromJson(it[stringPreferencesKey(key.name)], object : TypeToken<User>() {})
        }
    }

    override suspend fun save(data: User) {
        appDataStore.datastore.edit {
            it[stringPreferencesKey(key.name)] = gson.toJson(data)
        }
    }

    override suspend fun clear() {
        appDataStore.datastore.edit {
            it[stringPreferencesKey(key.name)] = ""
        }
    }

}
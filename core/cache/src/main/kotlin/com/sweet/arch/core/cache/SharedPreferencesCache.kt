package com.sweet.arch.core.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.sweet.arch.core.domain.cache.Cache
import com.sweet.arch.core.domain.enum.CacheKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesCache @Inject constructor(
    @ApplicationContext
    private val context: Context
) : Cache {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("iva_preference", Context.MODE_PRIVATE)

    override fun <T> saveObject(key: CacheKey, data: T) {
        saveString(
            key,
            Gson().toJson(data)
        )
    }

    override fun saveString(key: CacheKey, data: String) {
        sharedPreferences.edit {
            putString(key.name, data)
            apply()
        }
    }

    override fun saveInt(key: CacheKey, data: Int) {
        sharedPreferences.edit {
            putInt(key.name, data)
            apply()
        }
    }

    override fun saveBoolean(key: CacheKey, data: Boolean) {
        sharedPreferences.edit {
            putBoolean(key.name, data)
            apply()
        }
    }

    override fun saveLong(key: CacheKey, data: Long) {
        sharedPreferences.edit {
            putLong(key.name, data)
            apply()
        }
    }

    override fun getString(key: CacheKey): String {
        return sharedPreferences.getString(key.name, "") ?: ""
    }

    override fun getBoolean(key: CacheKey): Boolean {
        return sharedPreferences.getBoolean(key.name, false)
    }

    override fun getLong(key: CacheKey): Long {
        return sharedPreferences.getLong(key.name, -1)
    }

    override fun getInt(key: CacheKey): Int {
        return sharedPreferences.getInt(key.name, -1)
    }

    override fun getObject(key: CacheKey): String {
        return getString(key)
    }

    override fun clearData() {
        sharedPreferences.edit {
            clear()
            apply()
        }
    }
}
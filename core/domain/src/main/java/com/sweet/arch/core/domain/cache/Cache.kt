package com.sweet.arch.core.domain.cache

import com.sweet.arch.core.domain.enum.CacheKey


interface Cache {

    fun <T> saveObject(data: T, key: CacheKey)
    fun saveString(data: String, key: CacheKey)
    fun saveInt(data: Int, key: CacheKey)
    fun saveBoolean(data: Boolean, key: CacheKey)
    fun saveLong(data: Long, key: CacheKey)
    fun getString(key: CacheKey): String
    fun getBoolean(key: CacheKey): Boolean
    fun getLong(key: CacheKey): Long
    fun getInt(key: CacheKey): Int
    fun getObject(key: CacheKey): String
    fun clearData()

}
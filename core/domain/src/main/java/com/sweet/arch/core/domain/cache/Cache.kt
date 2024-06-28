package com.sweet.arch.core.domain.cache

import com.sweet.arch.core.domain.enum.CacheKey


interface Cache {

    fun <T> saveObject(key: CacheKey,data: T)
    fun saveString(key: CacheKey,data: String)
    fun saveInt(key: CacheKey,data: Int)
    fun saveBoolean(key: CacheKey,data: Boolean)
    fun saveLong(key: CacheKey,data: Long)
    fun getString(key: CacheKey): String
    fun getBoolean(key: CacheKey): Boolean
    fun getLong(key: CacheKey): Long
    fun getInt(key: CacheKey): Int
    fun getObject(key: CacheKey): String
    fun clearData()

}
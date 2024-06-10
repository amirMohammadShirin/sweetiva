package com.sweet.iva.core.domain.cache

import com.sweet.arch.core.domain.enum.CacheKey


interface Cache {

    fun <T> save(data: T, key: CacheKey)
    fun save(data: String, key: CacheKey)
    fun save(data: Int, key: CacheKey)
    fun save(data: Boolean, key: CacheKey)
    fun save(data: Long, key: CacheKey)
    fun getString(key: CacheKey): String?
    fun getBoolean(key: CacheKey): Boolean
    fun getLong(key: CacheKey): Long
    fun getInt(key: CacheKey): Int
    fun getObject(key: CacheKey): String?
    fun clearData()

}
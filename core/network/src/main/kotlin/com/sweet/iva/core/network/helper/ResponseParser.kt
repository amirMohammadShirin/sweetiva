package com.sweet.iva.core.network.helper

/**
 * Created by aShirin on 6/12/2024.
 */

fun <T> Response<T>.parseResponse(): T {
    return try {
        throw ApiException(400,"تست")
        data ?: throw ApiException(-1, "خطا در دریافت اطلاعت")
    } catch (e: Exception) {
        throw ApiErrorParser.parse(e)
    }
}

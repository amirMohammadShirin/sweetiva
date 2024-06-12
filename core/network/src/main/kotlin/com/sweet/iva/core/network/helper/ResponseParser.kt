package com.sweet.iva.core.network.helper

/**
 * Created by aShirin on 6/12/2024.
 */

fun <T> Response<T>.parseResponse(): T {
    return try {
        data ?: throw ApiException(-1, "خطا در دریافت اطلاعت")
    } catch (e: Exception) {
        throw ApiErrorParser.parse(e)
    }
}

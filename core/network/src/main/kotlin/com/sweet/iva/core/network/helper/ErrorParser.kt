package com.sweet.iva.core.network.helper

import com.google.gson.Gson
import retrofit2.HttpException

object ErrorParser {
    fun parse(e: Exception): ApiException {

        if (e is HttpException) {
            return parseHttpException(e)
        }

        if (e is ApiException)
            return e

        return ApiException(-1, "متن پیشفرض")

    }

    private fun parseHttpException(e: HttpException): ApiException {
        val response = e.response()
        val code = response?.code() ?: -1

        return try {
            Gson().fromJson(response?.errorBody()?.string(), ApiErrorResponse::class.java).error
        } catch (e: Exception) {
            ApiException(code, "خطا در دریافت اطلاعات")
        }
    }
}
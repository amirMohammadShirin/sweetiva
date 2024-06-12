package com.sweet.iva.core.network.helper

import com.google.gson.Gson
import com.sweet.iva.core.common.model.DisplayException
import retrofit2.HttpException

object ApiErrorParser {
    fun parse(e: Exception): DisplayException {

        if (e is HttpException) {
            return parseHttpException(e)
        }

        if (e is ApiException)
            return getDisplayException(e)

        return DisplayException(-1, "متن پیشفرض")

    }

    private fun getDisplayException(apiException: ApiException): DisplayException {
        return DisplayException(
            code = apiException.code,
            message = apiException.text
        )
    }

    private fun parseHttpException(e: HttpException): DisplayException {
        val response = e.response()
        val code = response?.code() ?: -1

        return try {

            val apiException =
                Gson().fromJson(response?.errorBody()?.string(), ApiErrorResponse::class.java).error

            return getDisplayException(
                apiException.copy(
                    code = code
                )
            )

        } catch (e: Exception) {
            DisplayException(code, "خطا در دریافت اطلاعات")
        }
    }
}
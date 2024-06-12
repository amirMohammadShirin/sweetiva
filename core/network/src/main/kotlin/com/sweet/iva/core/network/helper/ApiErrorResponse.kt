package com.sweet.iva.core.network.helper

import com.google.gson.annotations.SerializedName


data class ApiErrorResponse(
    val error: ApiException
)

data class ApiException(
    val code: Int,
    @SerializedName("message")
    val text: String,
) : Exception(text)

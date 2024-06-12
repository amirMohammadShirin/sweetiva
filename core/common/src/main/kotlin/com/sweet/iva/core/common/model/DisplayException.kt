package com.sweet.iva.core.common.model

class DisplayException(
    val code: Int = -1,
    message: String,
) : Exception(message)
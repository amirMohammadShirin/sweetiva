package com.sweet.iva.core.common.util

/**
 * Created by aShirin on 6/10/2024.
 */
enum class ValidationState {

    VALID,
    INVALID,
    EMPTY

}

object ValidationUtil {

    fun phoneNumber(value: String): ValidationState {

        if (value.isEmpty()) return ValidationState.EMPTY
        if (value.length != 11) return ValidationState.INVALID

        return ValidationState.VALID

    }

}



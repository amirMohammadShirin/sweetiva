package com.sweet.arch.core.domain.model.user

import com.sweet.iva.core.domain.model.exception.BusinessException

data class User internal constructor(
    val phoneNumber: PhoneNumber,
    val identity: Identity? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val nationalCode: String? = null
) {
    fun logOut(): User {
        return this.copy(
            identity = null
        )
    }

    fun login(identity: Identity.Companion.Argument): User {
        return this.copy(
            identity = Identity.create(identity)
        )
    }

    companion object {

        fun create(argument: Argument) =
            User(
                phoneNumber = PhoneNumber(argument.phoneNumber),
                firstName = argument.firstName,
                lastName = argument.lastName,
                nationalCode = argument.nationalCode,
            )

        data class Argument(
            val phoneNumber: String,
            val firstName: String? = null,
            val lastName: String? = null,
            val nationalCode: String? = null,
        )

    }

}

@JvmInline
value class PhoneNumber(val value: String) {

    init {
        emptyGuard()
        lengthGuard()
        numberOnlyGuard()
    }

    private fun numberOnlyGuard() {
        val pattern = "[0-9]{11}".toRegex()
        if (!value.matches(pattern)) throw BusinessException("phone number should contains number only")
    }

    private fun lengthGuard() {
        if (value.length != 11) throw BusinessException("phone number length should be 11")
    }

    private fun emptyGuard() {
        if (value.isEmpty()) throw BusinessException("empty phone number")
    }

}



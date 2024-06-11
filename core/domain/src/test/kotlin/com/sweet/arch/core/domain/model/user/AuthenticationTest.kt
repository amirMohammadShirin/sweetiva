package com.sweet.arch.core.domain.model.user

import com.sweet.iva.core.domain.model.exception.BusinessException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuthenticationTest {

    @Test(expected = BusinessException::class)
    fun `test empty access token`() {

        val token = ""

        Authentication.create(
            Authentication.Companion.Argument(
                accessToken = token,
                refreshToken = "refresh token value"
            )
        )

    }

    @Test(expected = BusinessException::class)
    fun `test empty refresh token`() {

        val token = ""

        Authentication.create(
            Authentication.Companion.Argument(
                accessToken = "access token value",
                refreshToken = token
            )
        )
    }

}
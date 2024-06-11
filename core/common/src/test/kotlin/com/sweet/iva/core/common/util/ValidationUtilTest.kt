package com.sweet.iva.core.common.util

import org.junit.Test
import kotlin.test.assertEquals


class ValidationUtilTest{

    @Test
    fun `empty phone number`(){

        val phoneNumber = ""

        assertEquals(
            ValidationState.EMPTY,
            ValidationUtil.phoneNumber(phoneNumber)
        )

    }
    @Test
    fun `invalid length phone number`(){

        val phoneNumber = "0912"

        assertEquals(
            ValidationState.INVALID,
            ValidationUtil.phoneNumber(phoneNumber)
        )

    }

}
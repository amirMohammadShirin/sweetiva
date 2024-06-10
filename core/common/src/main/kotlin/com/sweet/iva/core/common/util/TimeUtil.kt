package com.sweet.iva.core.common.util

import java.time.LocalTime

object TimeUtil {
    fun hourAsInt(hour: String): Int {
        val parsedTime = LocalTime.parse("$hour:00:00")
        return parsedTime.hour
    }

    fun minutesAsInt(minutes: String): Int {
        val parsedTime = LocalTime.parse("00:$minutes:00")
        return parsedTime.minute
    }
}
package com.sweet.iva.core.common.util

import java.time.Instant
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MILLISECOND
import java.util.Calendar.MINUTE
import java.util.Calendar.SECOND
import java.util.Date
import java.util.TimeZone

/**
 * Created by aShirin on 4/8/2024.
 */
object DateUtil {
    fun removeTimeParton(date: Long): Date {
        val parseDate = Date(date)
        return try {
            val calendar = Calendar.getInstance()
            calendar.time = parseDate
            calendar.set(HOUR_OF_DAY, 0)
            calendar.set(MINUTE, 0)
            calendar.set(SECOND, 0)
            calendar.set(MILLISECOND, 0)
            calendar.time
        } catch (e: Exception) {
            return parseDate
        }
    }

    fun equal(
        firstDate: Long,
        secondDate: Long,
    ): Boolean {
        try {
            val firstCastedDate = removeTimeParton(firstDate)
            val secondCastedDate = removeTimeParton(secondDate)
            return firstCastedDate == secondCastedDate
        } catch (e: Exception) {
            return false
        }
    }

    fun getLocalDate(timeStamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeStamp),
            TimeZone.getDefault().toZoneId(),
        )
    }

    fun getYear(timeStamp: Long): Int {
        return getLocalDate(timeStamp).year
    }

    fun getMonth(timeStamp: Long): Int {
        return getLocalDate(timeStamp).monthValue
    }

    fun getDay(timeStamp: Long): Int {
        return getLocalDate(timeStamp).dayOfMonth
    }
}

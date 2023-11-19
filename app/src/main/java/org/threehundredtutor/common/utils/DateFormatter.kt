package org.threehundredtutor.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {

    private const val TIME_ZONE = "UTC"
    private const val FORMAT_DATE_SERVER_RESPONSE = "yyyy-MM-dd'T'HH:mm:ss"

    private const val FORMAT_DATE_DEFAULT_YYYY_MM_DD_HH_MM_NEW = "HH:mm MM-dd-yy"

    fun String.toFormatDateDefault(): String =
        try {
            toDateFormatDateServerResponse().toStringFormatDateDefault()
        } catch (exception: Exception) {
            Date().toStringFormatDateDefault()
        }

    fun String.toDateFormatDateServerResponse(): Date {
        return try {
            SimpleDateFormat(FORMAT_DATE_SERVER_RESPONSE, Locale.getDefault()).parse(this)
                ?: Date()
        } catch (exception: Exception) {
            Date()
        }
    }

    private fun Date.toStringFormatDateDefault(): String =
        getSimpleDateFormat(FORMAT_DATE_DEFAULT_YYYY_MM_DD_HH_MM_NEW).format(this)

    private fun getSimpleDateFormat(format: String) =
        SimpleDateFormat(format, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone(TIME_ZONE)
        }
}


package org.threehundredtutor.common.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {

    private const val TIME_ZONE = "UTC"
    private const val FORMAT_DATE_SERVER_RESPONSE = "yyyy-MM-dd'T'HH:mm:ss"

    private const val FORMAT_DATE_DEFAULT_YYYY_MM_DD_HH_MM_NEW = "HH:mm dd-MM-yy"
    private const val DEFAULT_ADD_HOUR = 3 //Будет работать только с GMT+3

    fun String.toFormatDateDefault(needAddHours: Boolean = true): String =
        try {
            toDateFormatDateServerResponse().run {
                if (needAddHours) addHours(DEFAULT_ADD_HOUR)
                else this
            }.toStringFormatDateDefault()
        } catch (exception: Exception) {
            Date().toStringFormatDateDefault()
        }

    private fun String.toDateFormatDateServerResponse(): Date {
        return try {
            getSimpleDateFormat(FORMAT_DATE_SERVER_RESPONSE).parse(this) ?: Date()
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

    private fun Date.addHours(hour: Int): Date =
        Calendar.getInstance().also {
            it.time = this
            it.add(Calendar.HOUR_OF_DAY, hour)
        }.run {
            this.time
        }
}

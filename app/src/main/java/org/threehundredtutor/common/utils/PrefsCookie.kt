package org.threehundredtutor.common.utils

import android.content.Context
import android.util.Log
import javax.inject.Inject

class PrefsCookie @Inject constructor(
    private val context: Context
) {
    fun getCookie(): Set<String> {
        val cookie = context.getSharedPreferences(
            PREFS_COOKIE_FILE,
            Context.MODE_PRIVATE
        ).getStringSet(PREFS_COOKIE_KEY, setOf("")) ?: setOf("")

        Log.d(COOKIE_ANALYTICS_TAG, "$GET_COOKIE$cookie")
        return cookie
    }

    fun setCookie(cookieSet: Set<String>) = context.getSharedPreferences(
        PREFS_COOKIE_FILE,
        Context.MODE_PRIVATE
    ).edit().putStringSet(PREFS_COOKIE_KEY, cookieSet).apply().apply {
        Log.d(COOKIE_ANALYTICS_TAG, "$SET_COOKIE$cookieSet")
    }

    fun clear() = context.getSharedPreferences(
        PREFS_COOKIE_FILE,
        Context.MODE_PRIVATE
    ).edit().putStringSet(PREFS_COOKIE_KEY, setOf()).apply()
    
    companion object {
        // Log const
        private  const val COOKIE_ANALYTICS_TAG = "CookieAnalytics"
        private const val PREFS_COOKIE_FILE = "PREFS_COOKIE_FILE"
        private const val PREFS_COOKIE_KEY = "PREFS_COOKIE_KEY"
        private const val GET_COOKIE = "-Get->"
        private const val SET_COOKIE = "-Set->"
    }
}



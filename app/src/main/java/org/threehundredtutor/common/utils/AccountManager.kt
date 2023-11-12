package org.threehundredtutor.common.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.common.EMPTY_STRING
import javax.inject.Inject

class AccountManager @Inject constructor(
    private val context: Context
) {
    fun setAccountInfo(login: String, password: String) {
        context.getSharedPreferences(PREFS_SETTINGS_FILE, Context.MODE_PRIVATE).edit()
            .putString(PREFS_ACCOUNT_LOGIN_KEY, login)
            .putString(PREFS_ACCOUNT_PASSWORD_KEY, password).apply()
    }

    fun getAccountInfo(): Pair<String, String> {
        return with(
            context.getSharedPreferences(
                PREFS_SETTINGS_FILE,
                AppCompatActivity.MODE_PRIVATE
            )
        )
        {
            val login = getString(PREFS_ACCOUNT_LOGIN_KEY, EMPTY_STRING) ?: EMPTY_STRING
            val password = getString(PREFS_ACCOUNT_PASSWORD_KEY, EMPTY_STRING) ?: EMPTY_STRING

            login to password
        }
    }

    fun clearAccount() =
        context.getSharedPreferences(PREFS_SETTINGS_FILE, Context.MODE_PRIVATE).edit()
            .putString(PREFS_ACCOUNT_LOGIN_KEY, EMPTY_STRING)
            .putString(PREFS_ACCOUNT_PASSWORD_KEY, EMPTY_STRING).apply()

    fun getCookie(): Set<String> {
        val cookie = context.getSharedPreferences(
            PREFS_COOKIE_FILE,
            Context.MODE_PRIVATE
        ).getStringSet(PREFS_COOKIE_KEY, setOf()) ?: setOf()

        Log.d(COOKIE_ANALYTICS_TAG, "${GET_COOKIE}$cookie")
        return cookie
    }

    fun setCookie(cookieSet: Set<String>) = context.getSharedPreferences(
        PREFS_COOKIE_FILE,
        Context.MODE_PRIVATE
    ).edit().putStringSet(PREFS_COOKIE_KEY, cookieSet).apply().apply {
        Log.d(COOKIE_ANALYTICS_TAG, "${SET_COOKIE}$cookieSet")
    }

    fun clearCookie() = context.getSharedPreferences(
        PREFS_COOKIE_FILE,
        Context.MODE_PRIVATE
    ).edit().putStringSet(PREFS_COOKIE_KEY, setOf()).apply()

    companion object {
        private const val PREFS_SETTINGS_FILE = "PREFS_SETTINGS_FILE"
        private const val PREFS_ACCOUNT_LOGIN_KEY = "PREFS_ACCOUNT_LOGIN_KEY"
        private const val PREFS_ACCOUNT_PASSWORD_KEY = "PREFS_ACCOUNT_PASSWORD_KEY"
        private const val COOKIE_ANALYTICS_TAG = "CookieAnalytics"
        private const val PREFS_COOKIE_FILE = "PREFS_COOKIE_FILE"
        private const val PREFS_COOKIE_KEY = "PREFS_COOKIE_KEY"
        private const val GET_COOKIE = "-Get->"
        private const val SET_COOKIE = "-Set->"
    }
}



package org.threehundredtutor.common.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.R
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.KEY_THEME_DEFAULT
import javax.inject.Inject

// TODO использовать когда везде появяться виюмодели
class PrefsSettingsDagger @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val PREFS_SETTINGS_FILE = "PREFS_SETTINGS_FILE"
        private const val PREFS_FIRST_START_KEY = "PREFS_FIRST_START_KEY"
        private const val PREFS_THEME_KEY = "PREFS_THEME_KEY"
        private const val PREFS_ACCOUNT_LOGIN_KEY = "PREFS_ACCOUNT_LOGIN_KEY"
        private const val PREFS_DOMAIN_KEY = "PREFS_DOMAIN_KEY"
    }

    //TODO защитить преференсы как в аффилейте
    fun getFirstStartApp(): Boolean = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        Context.MODE_PRIVATE
    ).getBoolean(PREFS_FIRST_START_KEY, false)

    fun setFirstStartApp() = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        Context.MODE_PRIVATE
    ).edit().putBoolean(PREFS_FIRST_START_KEY, true).apply()

    fun setTheme(key: Int) {
        context.getSharedPreferences(
            PREFS_SETTINGS_FILE,
            Context.MODE_PRIVATE
        ).edit().putInt(PREFS_THEME_KEY, key).apply()
    }

    fun getThemePrefs(): Int {
        val themeKey = context.getSharedPreferences(
            PREFS_SETTINGS_FILE,
            AppCompatActivity.MODE_PRIVATE
        ).getInt(PREFS_THEME_KEY, KEY_THEME_DEFAULT)
        return when (themeKey) {
            KEY_THEME_DEFAULT -> R.style.Theme_300Tutor
            else -> R.style.Theme_300Tutor
        }
    }

    fun setAccountLogin(login: String) {
        context.getSharedPreferences(
            PREFS_SETTINGS_FILE,
            Context.MODE_PRIVATE
        ).edit().putString(PREFS_ACCOUNT_LOGIN_KEY, login).apply()
    }

    fun getAccountLogin(): String = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        AppCompatActivity.MODE_PRIVATE
    ).getString(PREFS_ACCOUNT_LOGIN_KEY, EMPTY_STRING) ?: EMPTY_STRING

    // TODO чтоб не забыть это домен клиента
    fun setDomain(domain: String) {
        context.getSharedPreferences(
            PREFS_SETTINGS_FILE,
            Context.MODE_PRIVATE
        ).edit().putString(PREFS_DOMAIN_KEY, domain).apply()
    }

    fun getCurrentDomain(): String = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        AppCompatActivity.MODE_PRIVATE
    ).getString(PREFS_DOMAIN_KEY, "") ?: ""
}


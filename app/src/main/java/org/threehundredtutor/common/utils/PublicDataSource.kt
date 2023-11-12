package org.threehundredtutor.common.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.R
import javax.inject.Inject

class PublicDataSource @Inject constructor(
    private val context: Context
) {
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

    fun getFirstStartApp(): Boolean = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        Context.MODE_PRIVATE
    ).getBoolean(PREFS_FIRST_START_KEY, true)

    fun setFirstStartApp() = context.getSharedPreferences(
        PREFS_SETTINGS_FILE,
        Context.MODE_PRIVATE
    ).edit().putBoolean(PREFS_FIRST_START_KEY, false).apply()

    companion object {
        private const val PREFS_FIRST_START_KEY = "PREFS_FIRST_START_KEY"
        private const val PREFS_SETTINGS_FILE = "PREFS_SETTINGS_FILE"
        private const val PREFS_THEME_KEY = "PREFS_THEME_KEY"
        private const val KEY_THEME_DEFAULT = 1
    }
}



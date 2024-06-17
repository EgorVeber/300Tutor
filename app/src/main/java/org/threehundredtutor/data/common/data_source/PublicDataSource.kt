package org.threehundredtutor.data.common.data_source

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.BuildConfig
import org.threehundredtutor.core.UiCoreStyle
import javax.inject.Inject

class PublicDataSource @Inject constructor(
    private val context: Context
) {

    init {
        Log.d("LocalDataSource", "PublicDataSource" + this.toString())
    }

    fun setTheme(key: Int) {
        context.getSharedPreferences(
            PUBLIC_PREFS_FILE,
            Context.MODE_PRIVATE
        ).edit().putInt(PREFS_THEME_KEY, key).apply()
    }

    fun getThemePrefs(): Int {
        val themeKey = context.getSharedPreferences(
            PUBLIC_PREFS_FILE,
            AppCompatActivity.MODE_PRIVATE
        ).getInt(PREFS_THEME_KEY, KEY_THEME_DEFAULT)
        return when (themeKey) {
            KEY_THEME_DEFAULT -> UiCoreStyle.Theme_300Tutor
            else -> UiCoreStyle.Theme_300Tutor
        }
    }

    fun getFirstStartApp(): Boolean = context.getSharedPreferences(
        PUBLIC_PREFS_FILE,
        Context.MODE_PRIVATE
    ).getBoolean(PREFS_FIRST_START_KEY, true)

    fun setFirstStartApp() = context.getSharedPreferences(
        PUBLIC_PREFS_FILE,
        Context.MODE_PRIVATE
    ).edit().putBoolean(PREFS_FIRST_START_KEY, false).apply()

    fun setDomain(domain: String) = context.getSharedPreferences(
        PUBLIC_PREFS_FILE,
        Context.MODE_PRIVATE
    ).edit().putString(PREFS_DOMAIN_KEY, domain).apply()

    fun getDomain(): String = context.getSharedPreferences(
        PUBLIC_PREFS_FILE,
        Context.MODE_PRIVATE
    ).getString(PREFS_DOMAIN_KEY, BuildConfig.BASE_URL) ?: BuildConfig.BASE_URL

    companion object {
        private const val PUBLIC_PREFS_FILE = "PUBLIC_PREFS_FILE"
        private const val PREFS_FIRST_START_KEY = "PREFS_FIRST_START_KEY"
        private const val PREFS_THEME_KEY = "PREFS_THEME_KEY"
        private const val PREFS_DOMAIN_KEY = "PREFS_DOMAIN_KEY"
        private const val KEY_THEME_DEFAULT = 1
    }
}
package org.threehundredtutor.domain.starter

interface ThemeRepository {
    fun getThemePrefs(): Int
    fun setTheme(themeKey: Int)
}

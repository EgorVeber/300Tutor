package org.v_points.domain.starter

interface ThemeRepository {
    fun getThemePrefs(): Int
    fun setTheme(themeKey: Int)
}

package com.v_points.app.domain.starter

interface ThemeRepository {
    fun getThemePrefs(): Int
    fun setTheme(themeKey: Int)
}

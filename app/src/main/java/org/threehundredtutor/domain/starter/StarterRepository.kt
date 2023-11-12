package org.threehundredtutor.domain.starter

interface StarterRepository {
    fun getFirstStartApp(): Boolean
    fun setFirstStartApp()
    fun getThemePrefs(): Int
    fun setTheme(themeKey: Int)
}

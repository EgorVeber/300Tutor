package org.threehundredtutor.data.starter

import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.domain.starter.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : ThemeRepository {
    override fun getThemePrefs(): Int = publicDataSource.getThemePrefs()
    override fun setTheme(themeKey: Int) {
        publicDataSource.setTheme(themeKey)
    }
}

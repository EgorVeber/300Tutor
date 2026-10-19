package org.v_points.data.starter

import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.domain.starter.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : ThemeRepository {
    override fun getThemePrefs(): Int = publicDataSource.getThemePrefs()
    override fun setTheme(themeKey: Int) {
        publicDataSource.setTheme(themeKey)
    }
}

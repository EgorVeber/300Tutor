package com.v_points.app.data.starter

import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.domain.starter.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : ThemeRepository {
    override fun getThemePrefs(): Int = publicDataSource.getThemePrefs()
    override fun setTheme(themeKey: Int) {
        publicDataSource.setTheme(themeKey)
    }
}

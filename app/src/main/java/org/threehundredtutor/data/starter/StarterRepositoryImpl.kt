package org.threehundredtutor.data.starter

import org.threehundredtutor.common.utils.PublicDataSource
import org.threehundredtutor.domain.starter.StarterRepository
import javax.inject.Inject

class StarterRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : StarterRepository {
    override fun getFirstStartApp(): Boolean = publicDataSource.getFirstStartApp()
    override fun setFirstStartApp() {
        publicDataSource.setFirstStartApp()
    }

    override fun getThemePrefs(): Int = publicDataSource.getThemePrefs()
    override fun setTheme(themeKey: Int) {
        publicDataSource.setTheme(themeKey)
    }
}

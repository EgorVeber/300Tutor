package org.v_points.domain.starter

import javax.inject.Inject

class SetThemePrefsUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(themeKey: Int) {
        themeRepository.setTheme(themeKey)
    }
}

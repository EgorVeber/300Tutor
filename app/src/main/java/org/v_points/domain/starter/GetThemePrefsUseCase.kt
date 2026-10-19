package org.v_points.domain.starter

import javax.inject.Inject

class GetThemePrefsUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Int = themeRepository.getThemePrefs()
}
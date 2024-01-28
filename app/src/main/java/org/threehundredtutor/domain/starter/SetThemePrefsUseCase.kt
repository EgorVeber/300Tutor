package org.threehundredtutor.domain.starter

import javax.inject.Inject

class SetThemePrefsUseCase @Inject constructor(
    private val starterRepository: StarterRepository
) {
     operator fun invoke(themeKey: Int) {
        starterRepository.setTheme(themeKey)
    }
}

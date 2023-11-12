package org.threehundredtutor.domain.starter

import javax.inject.Inject

class GetThemePrefsUseCase @Inject constructor(
    private val starterRepository: StarterRepository
) {
     operator fun invoke(): Int = starterRepository.getThemePrefs()
}

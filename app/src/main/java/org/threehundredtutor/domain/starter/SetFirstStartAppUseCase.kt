package org.threehundredtutor.domain.starter

import javax.inject.Inject

class SetFirstStartAppUseCase @Inject constructor(
    private val starterRepository: StarterRepository
) {
     operator fun invoke() {
        starterRepository.setFirstStartApp()
    }
}

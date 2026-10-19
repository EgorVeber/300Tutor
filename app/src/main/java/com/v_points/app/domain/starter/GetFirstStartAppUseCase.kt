package com.v_points.app.domain.starter

import javax.inject.Inject

class GetFirstStartAppUseCase @Inject constructor(
    private val starterRepository: StarterRepository
) {
    operator fun invoke(): Boolean = starterRepository.getFirstStartApp()
}

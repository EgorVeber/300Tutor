package com.v_points.app.domain.common

import com.v_points.app.data.common.ConfigRepository
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): ConfigModel = configRepository.getConfigModel()
}
package org.v_points.domain.common

import org.v_points.data.common.ConfigRepository
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): ConfigModel = configRepository.getConfigModel()
}
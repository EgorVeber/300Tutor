package org.threehundredtutor.domain.common

import org.threehundredtutor.data.common.ConfigRepository
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    operator fun invoke(): ConfigModel = configRepository.getConfigModel()
}
package org.threehundredtutor.domain.settings_app

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingAppStreamUseCase @Inject constructor(
    private val repository: SettingsAppRepository
) {
    suspend operator fun invoke(): Flow<SettingsAppModel> = repository.getSettingAppStream()
}
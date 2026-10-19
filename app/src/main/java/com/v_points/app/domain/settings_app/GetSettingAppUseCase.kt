package com.v_points.app.domain.settings_app

import javax.inject.Inject

class GetSettingAppUseCase @Inject constructor(
    private val repository: SettingsAppRepository
) {
    suspend operator fun invoke(force: Boolean): SettingsAppModel = repository.getSettingsApp(force)
}
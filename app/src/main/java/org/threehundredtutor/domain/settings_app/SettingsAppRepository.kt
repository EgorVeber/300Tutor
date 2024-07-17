package org.threehundredtutor.domain.settings_app

import kotlinx.coroutines.flow.Flow

interface SettingsAppRepository {
    suspend fun getSettingsApp(force: Boolean): SettingsAppModel
    suspend fun getSettingAppStream(): Flow<SettingsAppModel>
}

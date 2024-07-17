package org.threehundredtutor.data.settings_app

import kotlinx.coroutines.flow.Flow
import org.threehundredtutor.data.settings_app.model.toSettingsAppModel
import org.threehundredtutor.domain.settings_app.SettingsAppModel
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import javax.inject.Inject

class SettingsAppRepositoryImpl @Inject constructor(
    private val settingsAppRemoteDataSource: SettingsAppRemoteDataSource,
    private val settingsAppLocalDataSource: SettingsAppLocalDataSource,
) : SettingsAppRepository {
    override suspend fun getSettingsApp(force: Boolean): SettingsAppModel {
        val cache = settingsAppLocalDataSource.getSettings()
        return if (cache != SettingsAppModel.empty && !force) {
            cache
        } else {
            val settingsModel = settingsAppRemoteDataSource.getSettingsApp().toSettingsAppModel()
            settingsAppLocalDataSource.setSettings(settingsModel)
            settingsModel
        }
    }
    override suspend fun getSettingAppStream(): Flow<SettingsAppModel> {
       return settingsAppLocalDataSource.getSettingAppFlow()
    }
}
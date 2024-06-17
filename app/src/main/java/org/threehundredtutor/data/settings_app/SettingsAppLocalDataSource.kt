package org.threehundredtutor.data.settings_app

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.threehundredtutor.domain.settings_app.SettingsAppModel
import javax.inject.Inject

class SettingsAppLocalDataSource @Inject constructor(
) {

    private var settingsAppModel: SettingsAppModel = SettingsAppModel.empty
    private val settingsAppState = MutableStateFlow<SettingsAppModel>(SettingsAppModel.empty)

    init {
        Log.d("LocalDataSource", "SettingsAppLocalDataSource" + this.toString())
    }

    fun setSettings(settingsAppNewModel: SettingsAppModel) {
        settingsAppModel = settingsAppNewModel
        settingsAppState.update { settingsAppNewModel }
    }

    fun getSettings(): SettingsAppModel = settingsAppModel

    fun getSettingAppFlow(): Flow<SettingsAppModel> = settingsAppState

    fun clearAccount() {
        settingsAppModel = SettingsAppModel.empty
        settingsAppState.update { SettingsAppModel.empty }
    }
}
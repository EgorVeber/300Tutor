package com.v_points.app.data.settings_app

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.model.SettingsAppResponse
import javax.inject.Inject

class SettingsAppRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> SettingsAppService = {
        serviceGeneratorProvider.getService(SettingsAppService::class)
    }

    suspend fun getSettingsApp(): SettingsAppResponse = service().getSettingsApp()
}

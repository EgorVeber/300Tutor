package org.threehundredtutor.data.settings_app

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.settings_app.model.SettingsAppResponse
import javax.inject.Inject

class SettingsAppRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> SettingsAppService = {
        serviceGeneratorProvider.getService(SettingsAppService::class)
    }

    suspend fun getSettingsApp(): SettingsAppResponse = service().getSettingsApp()
}

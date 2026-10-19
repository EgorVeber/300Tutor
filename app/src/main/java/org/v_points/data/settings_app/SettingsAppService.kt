package org.v_points.data.settings_app

import org.v_points.data.settings_app.SettingsAppApi.TUTOR_SETTING_APPLICATION
import org.v_points.data.settings_app.model.SettingsAppResponse
import retrofit2.http.GET

interface SettingsAppService {
    @GET(TUTOR_SETTING_APPLICATION)
    suspend fun getSettingsApp(): SettingsAppResponse
}

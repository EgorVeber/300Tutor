package com.v_points.app.data.settings_app.model

import com.v_points.app.domain.settings_app.IconSettingsModel

fun IconSettingsResponse.toIconSettingsModel(): IconSettingsModel =
    IconSettingsModel(
        currentIconSetId = currentIconSetId.orEmpty(),
    )
package org.v_points.data.settings_app.model

import org.v_points.domain.settings_app.IconSettingsModel

fun IconSettingsResponse.toIconSettingsModel(): IconSettingsModel =
    IconSettingsModel(
        currentIconSetId = currentIconSetId.orEmpty(),
    )
package org.threehundredtutor.data.settings_app.model

import org.threehundredtutor.domain.settings_app.IconSettingsModel

fun IconSettingsResponse.toIconSettingsModel(): IconSettingsModel =
    IconSettingsModel(
        currentIconSetId = currentIconSetId.orEmpty(),
    )
package org.threehundredtutor.domain.settings_app

data class IconSettingsModel(
    val currentIconSetId: String
) {
    companion object {
        val empty = IconSettingsModel("")
    }
}
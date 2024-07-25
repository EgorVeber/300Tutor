package org.threehundredtutor.domain.settings_app

data class WebAppRoutesModel(
    val courseShop: String,
    val loginLinkFormat: String,
    val materials: String,
    val schedules: String,
    val toGroupFormat: String
) {
    companion object {
        val EMPTY = WebAppRoutesModel(
            courseShop = "",
            loginLinkFormat = "",
            materials = "",
            schedules = "",
            toGroupFormat = "",
        )
    }
}
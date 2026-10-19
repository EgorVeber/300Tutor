package org.v_points.domain.settings_app

data class TelephoneInputOptionsModel(
    val useInput: Boolean
) {
    companion object {
        val EMPTY = TelephoneInputOptionsModel(false)
    }
}
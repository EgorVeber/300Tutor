package org.v_points.domain.settings_app

class ImagePackModel(
    val error: String,
    val solutionFinished: String,
    val success: String
) {
    companion object {
        fun empty() = ImagePackModel("", "", "")
    }
}
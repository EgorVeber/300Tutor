package org.v_points.data.settings_app.model

import org.v_points.domain.settings_app.ImagePackModel

fun ImagePackResponse.toImagePackModel(): ImagePackModel =
    ImagePackModel(
        error = error.orEmpty(),
        solutionFinished = solutionFinished.orEmpty(),
        success = success.orEmpty(),
    )
package com.v_points.app.data.settings_app.model

import com.v_points.app.domain.settings_app.ImagePackModel

fun ImagePackResponse.toImagePackModel(): ImagePackModel =
    ImagePackModel(
        error = error.orEmpty(),
        solutionFinished = solutionFinished.orEmpty(),
        success = success.orEmpty(),
    )
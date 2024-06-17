package org.threehundredtutor.data.settings_app.model

import org.threehundredtutor.domain.settings_app.ImagePackModel

fun ImagePackResponse.toImagePackModel(): ImagePackModel =
    ImagePackModel(
        error = error.orEmpty(),
        solutionFinished = solutionFinished.orEmpty(),
        success = success.orEmpty(),
    )
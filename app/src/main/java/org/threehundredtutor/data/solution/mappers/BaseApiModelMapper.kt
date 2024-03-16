package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.BaseApiResponse
import org.threehundredtutor.domain.solution.models.BaseApiModel
import org.threehundredtutor.ui_common.util.orFalse

fun BaseApiResponse.toBaseApiModel(): BaseApiModel = BaseApiModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty(),
)
package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.BaseApiResponse
import org.threehundredtutor.domain.solution.models.BaseApiModel

fun BaseApiResponse.toBaseApiModel(): BaseApiModel = BaseApiModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty()
)
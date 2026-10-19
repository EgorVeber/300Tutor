package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.BaseApiResponse
import org.v_points.domain.solution.models.BaseApiModel
import org.v_points.ui_common.util.orFalse

fun BaseApiResponse.toBaseApiModel(): BaseApiModel = BaseApiModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty(),
)
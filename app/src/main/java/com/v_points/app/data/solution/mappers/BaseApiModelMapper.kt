package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.BaseApiResponse
import com.v_points.app.domain.solution.models.BaseApiModel
import com.v_points.app.ui_common.util.orFalse

fun BaseApiResponse.toBaseApiModel(): BaseApiModel = BaseApiModel(
    isSucceeded = isSucceeded.orFalse(),
    message = message.orEmpty(),
)
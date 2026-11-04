package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.StatResponse
import com.v_points.app.vpoint.fiersote.domain.StatModel

fun StatResponse.toStatModel(): StatModel {
    return StatModel(
        id = id ?: ("StatResponse id null"),
        statName = statName ?: error("StatResponse id null"),
        weight = weight ?: error("StatResponse id null")
    )
}

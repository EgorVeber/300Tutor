package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.GymResponse
import com.v_points.app.vpoint.fiersote.domain.GymModel

fun GymResponse.toGymModel(): GymModel {
    return GymModel(
        id = id ?: error("GymResponse id null"),
        name = name ?: error("GymResponse name null"),
        fullName = fullName ?: error("GymResponse fullName null"),
        latitude = latitude ?: error("GymResponse latitude null"),
        longitude = longitude ?: error("GymResponse longitude null")
    )
}
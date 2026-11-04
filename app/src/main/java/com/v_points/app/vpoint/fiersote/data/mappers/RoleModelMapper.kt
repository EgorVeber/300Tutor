package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import com.v_points.app.vpoint.fiersote.domain.RoleModel

fun RoleResponse.toRoleModel(): RoleModel {
    return RoleModel(
        id = id ?: error("RoleResponse id null"),
        roleType = roleType ?: error("RoleResponse roleType null")
    )
}
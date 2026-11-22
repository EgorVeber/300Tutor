package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.RoleResponse
import com.v_points.app.vpoint.fiersote.data.models.RoleType
import com.v_points.app.vpoint.fiersote.domain.RoleModel

fun RoleResponse.toRoleModel(): RoleModel {
    return RoleModel(
        id = id ?: error("RoleResponse id null"),
        roleType = when (roleType) {
            RoleType.Admin.name -> RoleType.Admin
            RoleType.User.name -> RoleType.User
            RoleType.Guest.name -> RoleType.Guest
            else -> error("RoleResponse roleType null")
        }
    )
}
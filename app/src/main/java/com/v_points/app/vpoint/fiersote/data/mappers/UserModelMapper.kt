
package com.v_points.app.vpoint.fiersote.data.mappers

import com.v_points.app.vpoint.fiersote.data.models.UserResponse
import com.v_points.app.vpoint.fiersote.domain.UserModel

fun UserResponse.toUserModel(): UserModel {
    return UserModel(
        id = id ?: error("UserResponse id null"),
        firstName = firstName ?: error("UserResponse firstName null"),
        lastName = lastName ?: error("UserResponse lastName null"),
        roleId = roleId ?: error("UserResponse roleId null")
    )
}
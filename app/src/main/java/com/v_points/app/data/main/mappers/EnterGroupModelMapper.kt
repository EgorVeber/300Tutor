package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.EnterGroupResponse
import com.v_points.app.domain.main.EnterGroupModel
import com.v_points.app.ui_common.util.BadRequestException

fun EnterGroupResponse.toEnterGroupModel(): EnterGroupModel = EnterGroupModel(
    succeeded = succeeded ?: throw BadRequestException(),
    errorMessage = errorMessage.orEmpty(),
    groupId = groupId ?: throw BadRequestException(),
    groupName = groupName ?: throw BadRequestException(),
)
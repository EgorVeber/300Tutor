package org.v_points.data.main.mappers

import org.v_points.data.main.response.EnterGroupResponse
import org.v_points.domain.main.EnterGroupModel
import org.v_points.ui_common.util.BadRequestException

fun EnterGroupResponse.toEnterGroupModel(): EnterGroupModel = EnterGroupModel(
    succeeded = succeeded ?: throw BadRequestException(),
    errorMessage = errorMessage.orEmpty(),
    groupId = groupId ?: throw BadRequestException(),
    groupName = groupName ?: throw BadRequestException(),
)
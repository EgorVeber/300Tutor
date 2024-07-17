package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.EnterGroupResponse
import org.threehundredtutor.domain.main.EnterGroupModel
import org.threehundredtutor.ui_common.util.BadRequestException

fun EnterGroupResponse.toEnterGroupModel(): EnterGroupModel = EnterGroupModel(
    succeeded = succeeded ?: throw BadRequestException(),
    errorMessage = errorMessage.orEmpty(),
    groupId = groupId ?: throw BadRequestException(),
    groupName = groupName ?: throw BadRequestException(),
)
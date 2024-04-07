package org.threehundredtutor.data.subject_workspace.mappers

import org.threehundredtutor.data.subject_workspace.model.WorkSpaceResponse
import org.threehundredtutor.domain.subject_workspace.models.WorkSpaceModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.util.BadRequestException

fun WorkSpaceResponse.toWorkSpaceModel(): WorkSpaceModel = WorkSpaceModel(
    description = description.orEmpty(),
    workSpaceId = workSpaceId ?: throw BadRequestException(),
    name = name ?: EMPTY_STRING
)
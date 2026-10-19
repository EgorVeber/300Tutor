package org.v_points.data.subject_workspace.mappers

import org.v_points.data.subject_workspace.model.WorkSpaceResponse
import org.v_points.domain.subject_workspace.models.WorkSpaceModel
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.BadRequestException

fun WorkSpaceResponse.toWorkSpaceModel(): WorkSpaceModel = WorkSpaceModel(
    description = description.orEmpty(),
    workSpaceId = workSpaceId ?: throw BadRequestException(),
    name = name ?: EMPTY_STRING
)
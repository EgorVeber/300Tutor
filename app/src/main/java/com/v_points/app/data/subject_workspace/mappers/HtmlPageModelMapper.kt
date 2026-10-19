package com.v_points.app.data.subject_workspace.mappers

import com.v_points.app.data.subject_workspace.model.WorkSpaceResponse
import com.v_points.app.domain.subject_workspace.models.WorkSpaceModel
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.BadRequestException

fun WorkSpaceResponse.toWorkSpaceModel(): WorkSpaceModel = WorkSpaceModel(
    description = description.orEmpty(),
    workSpaceId = workSpaceId ?: throw BadRequestException(),
    name = name ?: EMPTY_STRING
)
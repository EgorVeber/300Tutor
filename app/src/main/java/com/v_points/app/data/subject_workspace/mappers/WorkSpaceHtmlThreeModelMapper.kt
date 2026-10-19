package com.v_points.app.data.subject_workspace.mappers

import com.v_points.app.data.subject_workspace.model.WorkspaceHtmlThreeResponse
import com.v_points.app.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import com.v_points.app.ui_common.util.BadRequestException

fun WorkspaceHtmlThreeResponse.toWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel =
    WorkspaceHtmlThreeModel(
        directoryModel = directoryResponse?.toDirectoryModel() ?: throw BadRequestException(),
        workSpaceModel = workSpaceResponse?.toWorkSpaceModel() ?: throw BadRequestException(),
    )
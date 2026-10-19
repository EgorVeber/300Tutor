package org.v_points.data.subject_workspace.mappers

import org.v_points.data.subject_workspace.model.WorkspaceHtmlThreeResponse
import org.v_points.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import org.v_points.ui_common.util.BadRequestException

fun WorkspaceHtmlThreeResponse.toWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel =
    WorkspaceHtmlThreeModel(
        directoryModel = directoryResponse?.toDirectoryModel() ?: throw BadRequestException(),
        workSpaceModel = workSpaceResponse?.toWorkSpaceModel() ?: throw BadRequestException(),
    )
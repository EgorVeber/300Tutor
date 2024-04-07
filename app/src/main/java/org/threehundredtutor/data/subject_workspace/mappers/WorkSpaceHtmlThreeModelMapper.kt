package org.threehundredtutor.data.subject_workspace.mappers

import org.threehundredtutor.data.subject_workspace.model.WorkspaceHtmlThreeResponse
import org.threehundredtutor.domain.subject_workspace.models.WorkSpaceModel
import org.threehundredtutor.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import org.threehundredtutor.ui_common.util.BadRequestException

fun WorkspaceHtmlThreeResponse.toWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel =
    WorkspaceHtmlThreeModel(
        directoryModel = directoryResponse?.toDirectoryModel() ?: throw BadRequestException(),
        workSpaceModel = workSpaceResponse?.toWorkSpaceModel() ?: throw BadRequestException(),
    )
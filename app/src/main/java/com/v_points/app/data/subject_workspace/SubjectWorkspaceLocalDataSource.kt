package com.v_points.app.data.subject_workspace

import com.v_points.app.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import com.v_points.app.ui_common.EMPTY_STRING

class SubjectWorkspaceLocalDataSource {

    private var workspaceHtmlThreeModel: WorkspaceHtmlThreeModel? = null

    fun getWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel? = workspaceHtmlThreeModel
    fun getWorkspaceId(): String =
        workspaceHtmlThreeModel?.workSpaceModel?.workSpaceId ?: EMPTY_STRING

    fun setDirectory(workspaceHtmlThreeModel: WorkspaceHtmlThreeModel) {
        this.workspaceHtmlThreeModel = workspaceHtmlThreeModel
    }
}
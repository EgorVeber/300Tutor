package org.v_points.data.subject_workspace

import org.v_points.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import org.v_points.ui_common.EMPTY_STRING

class SubjectWorkspaceLocalDataSource {

    private var workspaceHtmlThreeModel: WorkspaceHtmlThreeModel? = null

    fun getWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel? = workspaceHtmlThreeModel
    fun getWorkspaceId(): String =
        workspaceHtmlThreeModel?.workSpaceModel?.workSpaceId ?: EMPTY_STRING

    fun setDirectory(workspaceHtmlThreeModel: WorkspaceHtmlThreeModel) {
        this.workspaceHtmlThreeModel = workspaceHtmlThreeModel
    }
}
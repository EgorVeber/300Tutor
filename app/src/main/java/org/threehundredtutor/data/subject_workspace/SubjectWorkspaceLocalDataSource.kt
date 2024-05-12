package org.threehundredtutor.data.subject_workspace

import org.threehundredtutor.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import org.threehundredtutor.ui_common.EMPTY_STRING

class SubjectWorkspaceLocalDataSource {

    private var workspaceHtmlThreeModel: WorkspaceHtmlThreeModel? = null

    fun getWorkspaceHtmlThreeModel(): WorkspaceHtmlThreeModel? = workspaceHtmlThreeModel
    fun getWorkspaceId(): String =
        workspaceHtmlThreeModel?.workSpaceModel?.workSpaceId ?: EMPTY_STRING

    fun setDirectory(workspaceHtmlThreeModel: WorkspaceHtmlThreeModel) {
        this.workspaceHtmlThreeModel = workspaceHtmlThreeModel
    }
}
package com.v_points.app.domain.subject_workspace

import com.v_points.app.domain.subject_workspace.models.DirectoryModel
import com.v_points.app.domain.subject_workspace.models.WorkspaceHtmlThreeModel

interface SubjectWorkspaceRepository {
    suspend fun getWorkSpace(workspaceId: String): WorkspaceHtmlThreeModel
    fun findDirectoryById(workspaceId: String): DirectoryModel?
    fun getWorkSpaceId(): String
}
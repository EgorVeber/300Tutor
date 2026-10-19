package org.v_points.domain.subject_workspace

import org.v_points.domain.subject_workspace.models.DirectoryModel
import org.v_points.domain.subject_workspace.models.WorkspaceHtmlThreeModel

interface SubjectWorkspaceRepository {
    suspend fun getWorkSpace(workspaceId: String): WorkspaceHtmlThreeModel
    fun findDirectoryById(workspaceId: String): DirectoryModel?
    fun getWorkSpaceId(): String
}
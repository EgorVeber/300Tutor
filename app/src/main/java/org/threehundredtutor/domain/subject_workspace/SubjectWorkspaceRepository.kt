package org.threehundredtutor.domain.subject_workspace

import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.domain.subject_workspace.models.WorkspaceHtmlThreeModel

interface SubjectWorkspaceRepository {
    suspend fun getWorkSpace(workspaceId: String): WorkspaceHtmlThreeModel
    fun findDirectoryById(workspaceId: String): DirectoryModel?
     fun getWorkSpaceId(): String
}

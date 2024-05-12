package org.threehundredtutor.domain.subject_workspace

import org.threehundredtutor.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import javax.inject.Inject

class GetWorkSpaceUseCase @Inject constructor(
    private val subjectWorkspaceRepository: SubjectWorkspaceRepository
) {
    suspend operator fun invoke(workspaceId: String): WorkspaceHtmlThreeModel =
        subjectWorkspaceRepository.getWorkSpace(workspaceId = workspaceId)
}
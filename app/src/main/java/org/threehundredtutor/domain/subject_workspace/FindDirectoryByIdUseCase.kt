package org.threehundredtutor.domain.subject_workspace

import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import javax.inject.Inject

class FindDirectoryByIdUseCase @Inject constructor(
    private val subjectWorkspaceRepository: SubjectWorkspaceRepository
) {
    operator fun invoke(workspaceId: String): DirectoryModel? {
        return subjectWorkspaceRepository.findDirectoryById(workspaceId = workspaceId)
    }
}
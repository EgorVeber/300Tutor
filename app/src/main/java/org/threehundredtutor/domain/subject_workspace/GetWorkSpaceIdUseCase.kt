package org.threehundredtutor.domain.subject_workspace

import javax.inject.Inject

class GetWorkSpaceIdUseCase @Inject constructor(
    private val subjectWorkspaceRepository: SubjectWorkspaceRepository
) {
    operator fun invoke(): String =
        subjectWorkspaceRepository.getWorkSpaceId()
}
package org.threehundredtutor.domain.subject_detailed

import org.threehundredtutor.domain.subject_detailed.models.MenuSubjectConfigModel
import javax.inject.Inject

class GetMenuSubjectConfigUseCase @Inject constructor(
    private val subjectDetailedRepository: SubjectDetailedRepository
) {
    suspend operator fun invoke(idOrAlias: String): MenuSubjectConfigModel =
        subjectDetailedRepository.getMenuSubjectConfig(idOrAlias = idOrAlias)
}
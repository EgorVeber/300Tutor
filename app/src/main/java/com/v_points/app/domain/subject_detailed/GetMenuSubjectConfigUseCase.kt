package com.v_points.app.domain.subject_detailed

import com.v_points.app.domain.subject_detailed.models.MenuSubjectConfigModel
import javax.inject.Inject

class GetMenuSubjectConfigUseCase @Inject constructor(
    private val subjectDetailedRepository: SubjectDetailedRepository
) {
    suspend operator fun invoke(idOrAlias: String): MenuSubjectConfigModel =
        subjectDetailedRepository.getMenuSubjectConfig(idOrAlias = idOrAlias)
}
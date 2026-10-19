package org.v_points.domain.subject_detailed

import org.v_points.domain.subject_detailed.models.MenuSubjectConfigModel

interface SubjectDetailedRepository {
    suspend fun getMenuSubjectConfig(idOrAlias: String): MenuSubjectConfigModel
}

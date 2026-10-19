package com.v_points.app.domain.subject_detailed

import com.v_points.app.domain.subject_detailed.models.MenuSubjectConfigModel

interface SubjectDetailedRepository {
    suspend fun getMenuSubjectConfig(idOrAlias: String): MenuSubjectConfigModel
}

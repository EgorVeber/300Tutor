package org.threehundredtutor.domain.subject_detailed

import org.threehundredtutor.domain.subject_detailed.models.MenuSubjectConfigModel

interface SubjectDetailedRepository {
    suspend fun getMenuSubjectConfig(idOrAlias: String): MenuSubjectConfigModel
}

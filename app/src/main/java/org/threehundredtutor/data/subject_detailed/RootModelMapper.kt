package org.threehundredtutor.data.subject_detailed

import org.threehundredtutor.data.subject_detailed.models.RootResponse
import org.threehundredtutor.domain.subject_detailed.models.MenuSubjectConfigModel
import org.threehundredtutor.domain.subject_detailed.models.SubjectMenuItemType

fun RootResponse.toRootModel(): MenuSubjectConfigModel =
    MenuSubjectConfigModel(
        subjectMenuItems = children?.map { childrenResponse ->
            childrenResponse.toRootModel()
        }.orEmpty(),
        path = path.orEmpty(),
        text = text.orEmpty(),
        type = SubjectMenuItemType.fromMenuItem(type.orEmpty()),
        workSpaceId = workSpaceId.orEmpty(),
    )

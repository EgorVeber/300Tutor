package com.v_points.app.data.subject_detailed

import com.v_points.app.data.subject_detailed.models.RootResponse
import com.v_points.app.domain.subject_detailed.models.MenuSubjectConfigModel
import com.v_points.app.domain.subject_detailed.models.SubjectMenuItemType

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

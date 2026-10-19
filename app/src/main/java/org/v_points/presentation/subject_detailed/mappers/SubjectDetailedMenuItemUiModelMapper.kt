package org.v_points.presentation.subject_detailed.mappers

import org.v_points.domain.subject_detailed.models.MenuSubjectConfigModel
import org.v_points.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel

fun MenuSubjectConfigModel.toSubjectDetailedMenuItemUiModel(): SubjectMenuItemUiModel =
    SubjectMenuItemUiModel(
        subjectMenuItems = subjectMenuItems.map { rootModel ->
            rootModel.toSubjectDetailedMenuItemUiModel()
        },
        path = path,
        text = text,
        type = type,
        workSpaceId = workSpaceId
    )
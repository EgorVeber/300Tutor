package com.v_points.app.presentation.subject_detailed.mappers

import com.v_points.app.domain.subject_detailed.models.MenuSubjectConfigModel
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel

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
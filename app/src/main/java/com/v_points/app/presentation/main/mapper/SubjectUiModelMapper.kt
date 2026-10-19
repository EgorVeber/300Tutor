package com.v_points.app.presentation.main.mapper

import com.v_points.app.domain.subject_tests.models.SubjectModel
import com.v_points.app.presentation.main.ui_models.SubjectUiModel

fun SubjectModel.toSubjectUiModel(iconPath: String): SubjectUiModel = SubjectUiModel(
    subjectId = id,
    subjectName = name,
    checked = true,
    alias = alias,
    iconPath = iconPath
)
package org.v_points.presentation.main.mapper

import org.v_points.domain.subject_tests.models.SubjectModel
import org.v_points.presentation.main.ui_models.SubjectUiModel

fun SubjectModel.toSubjectUiModel(iconPath: String): SubjectUiModel = SubjectUiModel(
    subjectId = id,
    subjectName = name,
    checked = true,
    alias = alias,
    iconPath = iconPath
)
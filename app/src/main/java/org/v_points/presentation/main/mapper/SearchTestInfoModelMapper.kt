package org.v_points.presentation.main.mapper

import org.v_points.domain.subject_tests.models.SearchTestInfoModel
import org.v_points.presentation.subject_tests.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId,
        questionsCount = questionsCount
    )
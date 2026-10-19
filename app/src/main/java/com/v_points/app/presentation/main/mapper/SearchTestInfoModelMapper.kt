package com.v_points.app.presentation.main.mapper

import com.v_points.app.domain.subject_tests.models.SearchTestInfoModel
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId,
        questionsCount = questionsCount
    )
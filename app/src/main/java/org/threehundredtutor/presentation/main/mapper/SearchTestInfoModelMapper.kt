package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.subject_tests.models.SearchTestInfoModel
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId,
        questionsCount = questionsCount
    )
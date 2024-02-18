package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.main.models.SearchTestInfoModel
import org.threehundredtutor.presentation.main.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId
    )
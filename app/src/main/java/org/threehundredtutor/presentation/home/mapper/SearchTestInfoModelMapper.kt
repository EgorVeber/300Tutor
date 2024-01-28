package org.threehundredtutor.presentation.home.mapper

import org.threehundredtutor.domain.subject.models.SearchTestInfoModel
import org.threehundredtutor.presentation.home.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId
    )
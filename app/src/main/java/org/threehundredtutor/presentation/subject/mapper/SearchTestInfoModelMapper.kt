package org.threehundredtutor.presentation.subject.mapper

import org.threehundredtutor.domain.subject.models.SearchTestInfoModel
import org.threehundredtutor.presentation.subject.ui_models.SubjectTestUiModel

fun SearchTestInfoModel.toSubjectTestUiModel(subjectId: String): SubjectTestUiModel =
    SubjectTestUiModel(
        subjectTestId = id,
        subjectTestName = name,
        subjectId = subjectId
    )
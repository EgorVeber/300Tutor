package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.subject_tests.models.SubjectModel
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel

fun SubjectModel.toSubjectUiModel(): SubjectUiModel = SubjectUiModel(
    subjectId = id,
    subjectName = name,
    checked = true,
    alias = alias
)
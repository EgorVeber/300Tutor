package org.threehundredtutor.presentation.home.ui_models

import org.threehundredtutor.domain.subject.models.SubjectModel

fun SubjectModel.toSubjectUiModel(): SubjectUiModel = SubjectUiModel(
    subjectId = id,
    subjectName = name,
    checked = true
)
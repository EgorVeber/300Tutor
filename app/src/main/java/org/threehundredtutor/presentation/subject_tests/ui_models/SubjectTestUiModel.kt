package org.threehundredtutor.presentation.subject_tests.ui_models

data class SubjectTestUiModel(
    val subjectTestId: String,
    val subjectTestName: String,
    val subjectId: String,
    val questionsCount: Int
) : SubjectTestsUiItem
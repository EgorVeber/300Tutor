package org.threehundredtutor.presentation.main.ui_models

data class SubjectUiModel(
    val subjectId: String,
    val subjectName: String,
    val checked: Boolean,
    val alias: String
) : MainUiItem

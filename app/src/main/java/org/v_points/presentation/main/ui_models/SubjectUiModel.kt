package org.v_points.presentation.main.ui_models

data class SubjectUiModel(
    val subjectId: String,
    val subjectName: String,
    val checked: Boolean,
    val alias: String,
    val iconPath: String
) : MainUiItem

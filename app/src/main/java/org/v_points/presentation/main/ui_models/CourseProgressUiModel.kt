package org.v_points.presentation.main.ui_models

data class CourseProgressUiModel(
    val groupId: String,
    val groupName: String,
    val progressPercents: Int,
    val iconPath: String
) : MainUiItem

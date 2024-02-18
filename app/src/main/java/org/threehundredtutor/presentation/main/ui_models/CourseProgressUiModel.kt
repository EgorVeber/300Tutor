package org.threehundredtutor.presentation.main.ui_models

data class CourseProgressUiModel(
    val groupId: String,
    val groupName: String,
    val progressPercents: Int,
) : MainUiItem

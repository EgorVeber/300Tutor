package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel

fun GroupWithCourseProgressModel.toCourseUiModel(iconPath: String): CourseUiModel =
    CourseUiModel(
        groupId = groupId,
        groupName = groupName,
        iconPath = iconPath
    )
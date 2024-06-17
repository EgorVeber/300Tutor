package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel

fun GroupWithCourseProgressModel.toCourseUiModel(applicationUrl: String): CourseUiModel =
    CourseUiModel(
        groupId = groupId,
        groupName = groupName,
        iconPath = if (iconModel.serverPath.isEmpty()) "" else applicationUrl + "/" + iconModel.serverPath
    )
package org.v_points.presentation.main.mapper

import org.v_points.domain.main.models.GroupWithCourseProgressModel
import org.v_points.presentation.main.ui_models.CourseUiModel

fun GroupWithCourseProgressModel.toCourseUiModel(iconPath: String): CourseUiModel =
    CourseUiModel(
        groupId = groupId,
        groupName = groupName,
        iconPath = iconPath
    )
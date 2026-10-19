package org.v_points.presentation.main.mapper

import org.v_points.domain.main.models.GroupWithCourseProgressModel
import org.v_points.presentation.main.ui_models.CourseProgressUiModel

fun GroupWithCourseProgressModel.toCourseProgressUiModel(iconPath: String): CourseProgressUiModel =
    CourseProgressUiModel(
        groupId = groupId,
        groupName = groupName,
        progressPercents = courseProgressModel.progressPercents,
        iconPath = iconPath
    )
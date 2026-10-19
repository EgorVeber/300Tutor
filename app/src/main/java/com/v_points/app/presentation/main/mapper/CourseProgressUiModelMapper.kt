package com.v_points.app.presentation.main.mapper

import com.v_points.app.domain.main.models.GroupWithCourseProgressModel
import com.v_points.app.presentation.main.ui_models.CourseProgressUiModel

fun GroupWithCourseProgressModel.toCourseProgressUiModel(iconPath: String): CourseProgressUiModel =
    CourseProgressUiModel(
        groupId = groupId,
        groupName = groupName,
        progressPercents = courseProgressModel.progressPercents,
        iconPath = iconPath
    )
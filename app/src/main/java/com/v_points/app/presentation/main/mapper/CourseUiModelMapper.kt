package com.v_points.app.presentation.main.mapper

import com.v_points.app.domain.main.models.GroupWithCourseProgressModel
import com.v_points.app.presentation.main.ui_models.CourseUiModel

fun GroupWithCourseProgressModel.toCourseUiModel(iconPath: String): CourseUiModel =
    CourseUiModel(
        groupId = groupId,
        groupName = groupName,
        iconPath = iconPath
    )
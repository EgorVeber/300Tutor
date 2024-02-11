package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel

fun GroupWithCourseProgressModel.toCourseUiModel(): CourseUiModel =
    CourseUiModel(
        groupId = groupId,
        groupName = groupName
    )

fun GroupWithCourseProgressModel.toCourseProgressUiModel(): CourseProgressUiModel =
    CourseProgressUiModel(
        groupId = groupId,
        groupName = groupName,
        progressPercents = courseProgressModel.progressPercents
    )
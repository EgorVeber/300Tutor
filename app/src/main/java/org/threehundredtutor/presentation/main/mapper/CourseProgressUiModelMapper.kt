package org.threehundredtutor.presentation.main.mapper

import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel

fun GroupWithCourseProgressModel.toCourseProgressUiModel(): CourseProgressUiModel =
    CourseProgressUiModel(
        groupId = groupId,
        groupName = groupName,
        progressPercents = courseProgressModel.progressPercents,
    )
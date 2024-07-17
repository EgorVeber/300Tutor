package org.threehundredtutor.domain.main.models

import org.threehundredtutor.domain.subject_tests.models.IconModel

data class GroupWithCourseProgressModel(
    val groupId: String,
    val groupName: String,
    val groupNameHtml: String,
    val groupUseHtmlForName: Boolean,
    val isBlocked: Boolean,
    val shouldBlockOnUtc: String,
    val useCourse: Boolean,
    val courseProgressModel: CourseProgressModel,
    val iconModel: IconModel,
)
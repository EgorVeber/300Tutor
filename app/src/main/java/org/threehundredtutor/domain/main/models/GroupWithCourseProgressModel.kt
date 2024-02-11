package org.threehundredtutor.domain.main.models

data class GroupWithCourseProgressModel(
    val groupId: String,
    val groupName: String,
    val groupNameHtml: String,
    val groupUseHtmlForName: Boolean,
    val isBlocked: Boolean,
    val shouldBlockOnUtc: String,
    val useCourse: Boolean,
    val courseProgressModel: CourseProgressModel,
)
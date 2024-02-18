package org.threehundredtutor.domain.main.models

data class GroupWithCourseModel(
    val count: Int,
    val list: List<GroupWithCourseProgressModel>,
    val offSet: Int,
    val totalCount: Int
)
package org.v_points.domain.main.models

data class GroupWithCourseModel(
    val count: Int,
    val list: List<GroupWithCourseProgressModel>,
    val offSet: Int,
    val totalCount: Int
)
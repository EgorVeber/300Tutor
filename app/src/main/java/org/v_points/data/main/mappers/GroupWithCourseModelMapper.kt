package org.v_points.data.main.mappers

import org.v_points.data.main.response.GroupWithCourseProgressResponse
import org.v_points.data.main.response.GroupWithCourseResponse
import org.v_points.domain.main.models.GroupWithCourseModel
import org.v_points.ui_common.util.orDefaultNotValidValue

fun GroupWithCourseResponse.toGroupWithCourseModel(): GroupWithCourseModel =
    GroupWithCourseModel(
        count = count.orDefaultNotValidValue(),
        list = list?.map { groupWithCourseProgressResponse: GroupWithCourseProgressResponse ->
            groupWithCourseProgressResponse.toGroupWithCourseProgressModel()
        }.orEmpty(),
        offSet = offSet.orDefaultNotValidValue(),
        totalCount = totalCount.orDefaultNotValidValue()
    )
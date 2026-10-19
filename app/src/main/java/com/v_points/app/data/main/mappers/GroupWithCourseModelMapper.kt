package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.GroupWithCourseProgressResponse
import com.v_points.app.data.main.response.GroupWithCourseResponse
import com.v_points.app.domain.main.models.GroupWithCourseModel
import com.v_points.app.ui_common.util.orDefaultNotValidValue

fun GroupWithCourseResponse.toGroupWithCourseModel(): GroupWithCourseModel =
    GroupWithCourseModel(
        count = count.orDefaultNotValidValue(),
        list = list?.map { groupWithCourseProgressResponse: GroupWithCourseProgressResponse ->
            groupWithCourseProgressResponse.toGroupWithCourseProgressModel()
        }.orEmpty(),
        offSet = offSet.orDefaultNotValidValue(),
        totalCount = totalCount.orDefaultNotValidValue()
    )
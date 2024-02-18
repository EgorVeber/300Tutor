package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.data.main.response.GroupWithCourseProgressResponse
import org.threehundredtutor.data.main.response.GroupWithCourseResponse
import org.threehundredtutor.domain.main.models.GroupWithCourseModel

fun GroupWithCourseResponse.toGroupWithCourseModel(): GroupWithCourseModel =
    GroupWithCourseModel(
        count = count.orDefaultNotValidValue(),
        list = list?.map { groupWithCourseProgressResponse: GroupWithCourseProgressResponse ->
            groupWithCourseProgressResponse.toGroupWithCourseProgressModel()
        }.orEmpty(),
        offSet = offSet.orDefaultNotValidValue(),
        totalCount = totalCount.orDefaultNotValidValue()
    )
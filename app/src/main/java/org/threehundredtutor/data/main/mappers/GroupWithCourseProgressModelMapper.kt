package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.main.response.GroupWithCourseProgressResponse
import org.threehundredtutor.domain.main.models.CourseProgressModel
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel

fun GroupWithCourseProgressResponse.toGroupWithCourseProgressModel(): GroupWithCourseProgressModel =
    GroupWithCourseProgressModel(
        groupId = groupId ?: throw BadRequestException(),
        groupName = groupName.orEmpty(),
        groupNameHtml = groupNameHtml.orEmpty(),
        groupUseHtmlForName = groupUseHtmlForName.orFalse(),
        isBlocked = isBlocked.orFalse(),
        shouldBlockOnUtc = shouldBlockOnUtc.orEmpty(),
        useCourse = useCourse ?: throw BadRequestException(),
        courseProgressModel = courseProgressResponse?.toCourseProgressModel()
            ?: CourseProgressModel.EMPTY
    )
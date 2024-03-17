package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.GroupWithCourseProgressResponse
import org.threehundredtutor.domain.main.models.CourseProgressModel
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import org.threehundredtutor.ui_common.util.BadRequestException
import org.threehundredtutor.ui_common.util.orFalse

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
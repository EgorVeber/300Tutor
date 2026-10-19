package org.v_points.data.main.mappers

import org.v_points.data.main.response.GroupWithCourseProgressResponse
import org.v_points.data.subject_tests.models.toIconModel
import org.v_points.domain.main.models.CourseProgressModel
import org.v_points.domain.main.models.GroupWithCourseProgressModel
import org.v_points.domain.subject_tests.models.IconModel
import org.v_points.ui_common.util.BadRequestException
import org.v_points.ui_common.util.orFalse

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
            ?: CourseProgressModel.EMPTY,
        iconModel = iconResponse?.toIconModel() ?: IconModel.empty
    )
package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.GroupWithCourseProgressResponse
import com.v_points.app.data.subject_tests.models.toIconModel
import com.v_points.app.domain.main.models.CourseProgressModel
import com.v_points.app.domain.main.models.GroupWithCourseProgressModel
import com.v_points.app.domain.subject_tests.models.IconModel
import com.v_points.app.ui_common.util.BadRequestException
import com.v_points.app.ui_common.util.orFalse

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
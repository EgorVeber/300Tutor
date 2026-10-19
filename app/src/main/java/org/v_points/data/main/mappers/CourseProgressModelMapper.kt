package org.v_points.data.main.mappers

import org.v_points.data.main.response.CourseProgressResponse
import org.v_points.domain.main.models.CourseProgressModel
import org.v_points.ui_common.util.orDefaultNotValidValue

fun CourseProgressResponse.toCourseProgressModel(): CourseProgressModel =
    CourseProgressModel(
        currentProgress = currentProgress.orDefaultNotValidValue(),
        progressPercents = progressPercents.orDefaultNotValidValue(),
        totalWeight = totalWeight.orDefaultNotValidValue()
    )
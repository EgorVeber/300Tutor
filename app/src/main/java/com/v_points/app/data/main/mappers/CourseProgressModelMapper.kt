package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.CourseProgressResponse
import com.v_points.app.domain.main.models.CourseProgressModel
import com.v_points.app.ui_common.util.orDefaultNotValidValue

fun CourseProgressResponse.toCourseProgressModel(): CourseProgressModel =
    CourseProgressModel(
        currentProgress = currentProgress.orDefaultNotValidValue(),
        progressPercents = progressPercents.orDefaultNotValidValue(),
        totalWeight = totalWeight.orDefaultNotValidValue()
    )
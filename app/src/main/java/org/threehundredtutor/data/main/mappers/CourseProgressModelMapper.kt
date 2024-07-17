package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.CourseProgressResponse
import org.threehundredtutor.domain.main.models.CourseProgressModel
import org.threehundredtutor.ui_common.util.orDefaultNotValidValue

fun CourseProgressResponse.toCourseProgressModel(): CourseProgressModel =
    CourseProgressModel(
        currentProgress = currentProgress.orDefaultNotValidValue(),
        progressPercents = progressPercents.orDefaultNotValidValue(),
        totalWeight = totalWeight.orDefaultNotValidValue()
    )
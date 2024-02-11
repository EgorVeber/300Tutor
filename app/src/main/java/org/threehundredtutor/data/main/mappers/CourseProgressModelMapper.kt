package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.data.main.response.CourseProgressResponse
import org.threehundredtutor.domain.main.models.CourseProgressModel

fun CourseProgressResponse.toCourseProgressModel(): CourseProgressModel =
    CourseProgressModel(
        currentProgress = currentProgress.orDefaultNotValidValue(),
        progressPercents = progressPercents.orDefaultNotValidValue(),
        totalWeight = totalWeight.orDefaultNotValidValue()
    )
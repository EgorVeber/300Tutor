package com.v_points.app.domain.main.models

import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT

data class CourseProgressModel(
    val currentProgress: Int,
    val progressPercents: Int,
    val totalWeight: Int
) {
    companion object {
        val EMPTY = CourseProgressModel(
            currentProgress = DEFAULT_NOT_VALID_VALUE_INT,
            progressPercents = DEFAULT_NOT_VALID_VALUE_INT,
            totalWeight = DEFAULT_NOT_VALID_VALUE_INT
        )
    }
}
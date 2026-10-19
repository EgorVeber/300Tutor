package com.v_points.app.domain.solution.models.test_model

import com.v_points.app.ui_common.EMPTY_STRING


data class TypeAnswerWithErrorsModel(
    val rightAnswer: String
) {
    companion object {
        val EMPTY = TypeAnswerWithErrorsModel(
            rightAnswer = EMPTY_STRING
        )
    }
}
package org.v_points.domain.solution.models.test_model

import org.v_points.ui_common.EMPTY_STRING


data class TypeAnswerWithErrorsModel(
    val rightAnswer: String
) {
    companion object {
        val EMPTY = TypeAnswerWithErrorsModel(
            rightAnswer = EMPTY_STRING
        )
    }
}
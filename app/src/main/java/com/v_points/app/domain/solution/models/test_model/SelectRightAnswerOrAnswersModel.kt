package com.v_points.app.domain.solution.models.test_model

import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.EMPTY_STRING

data class SelectRightAnswerOrAnswersModel(
    val answersList: List<AnswerSelectRightModel>,
    val rightAnswersCount: Int,
    val selectRightAnswerTitle: String,
) {
    companion object {
        val EMPTY = SelectRightAnswerOrAnswersModel(
            answersList = emptyList(),
            rightAnswersCount = DEFAULT_NOT_VALID_VALUE_INT,
            selectRightAnswerTitle = EMPTY_STRING
        )
    }
}
package org.threehundredtutor.domain.solution.models.solution_models

import org.threehundredtutor.ui_common.EMPTY_STRING

data class AnswerModel(
    val answerOrAnswers: String,
    val isChecked: Boolean,
    val pointsValidationModel: PointsValidationModel,
    val questionId: String,
    val questionVersionId: String,
    val answerValidationResultType: AnswerValidationResultType
) {
    fun isHaveAnswer() = this != EMPTY && this.isChecked

    companion object {

        val EMPTY = AnswerModel(
            answerOrAnswers = EMPTY_STRING,
            isChecked = false,
            pointsValidationModel = PointsValidationModel.EMPTY,
            questionId = EMPTY_STRING,
            questionVersionId = EMPTY_STRING,
            answerValidationResultType = AnswerValidationResultType.UNKNOWN
        )
    }
}
package com.v_points.app.domain.solution.models.points

import com.v_points.app.domain.solution.models.solution_models.AnswerValidationResultType

data class SolutionPointsQuestionModel(
    val answerPoints: Int,
    val description: String,
    val isValidated: Boolean,
    val questionId: String,
    val questionTotalPoints: Int,
    val resultType: AnswerValidationResultType,
    val sourceType: String,
    val validatorId: String
)
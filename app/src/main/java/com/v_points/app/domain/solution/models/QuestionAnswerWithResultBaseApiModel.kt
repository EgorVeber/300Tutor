package com.v_points.app.domain.solution.models

import com.v_points.app.domain.solution.models.solution_models.AnswerModel

data class QuestionAnswerWithResultBaseApiModel(
    val isSucceeded: Boolean,
    val message: String,
    val answerModel: AnswerModel
)
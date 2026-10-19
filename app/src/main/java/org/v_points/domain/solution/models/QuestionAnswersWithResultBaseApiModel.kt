package org.v_points.domain.solution.models

import org.v_points.domain.solution.models.solution_models.AnswerModel

data class QuestionAnswersWithResultBaseApiModel(
    val isSucceeded: Boolean,
    val message: String,
    val answersModel: List<AnswerModel>
)
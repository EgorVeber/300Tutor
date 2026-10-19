package com.v_points.app.domain.solution.models.solution_models

data class SolutionModel(
    val answerModelList: List<AnswerModel>,
    val hasAnswersInProcess: Boolean
)
package org.threehundredtutor.domain.solution.models.solution_models

data class SolutionModel(
    val answerModelList: List<AnswerModel>,
    val hasAnswersInProcess: Boolean
)
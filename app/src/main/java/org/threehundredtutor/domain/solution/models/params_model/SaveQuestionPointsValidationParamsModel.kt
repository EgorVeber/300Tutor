package org.threehundredtutor.domain.solution.models.params_model

class SaveQuestionPointsValidationParamsModel(
    val questionSolutionIdParamsModel: QuestionSolutionIdParamsModel,
    val answerPoints: Int,
    val questionTotalPoints: Int,
    val description: String
)
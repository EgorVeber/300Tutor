package org.threehundredtutor.data.solution.mappers.request

import org.threehundredtutor.data.solution.models.request.SaveQuestionPointsValidationRequest
import org.threehundredtutor.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel

fun SaveQuestionPointsValidationParamsModel.toSaveQuestionPointsValidationRequest(): SaveQuestionPointsValidationRequest =
    SaveQuestionPointsValidationRequest(
        questionSolutionIdRequest = questionSolutionIdParamsModel.toQuestionSolutionIdRequest(),
        answerPoints = answerPoints,
        questionTotalPoints = questionTotalPoints,
        description = description,
    )
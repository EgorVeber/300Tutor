package org.v_points.data.solution.mappers.request

import org.v_points.data.solution.models.request.SaveQuestionPointsValidationRequest
import org.v_points.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel

fun SaveQuestionPointsValidationParamsModel.toSaveQuestionPointsValidationRequest(): SaveQuestionPointsValidationRequest =
    SaveQuestionPointsValidationRequest(
        questionSolutionIdRequest = questionSolutionIdParamsModel.toQuestionSolutionIdRequest(),
        answerPoints = answerPoints,
        questionTotalPoints = questionTotalPoints,
        description = description,
    )
package com.v_points.app.data.solution.mappers.request

import com.v_points.app.data.solution.models.request.SaveQuestionPointsValidationRequest
import com.v_points.app.domain.solution.models.params_model.SaveQuestionPointsValidationParamsModel

fun SaveQuestionPointsValidationParamsModel.toSaveQuestionPointsValidationRequest(): SaveQuestionPointsValidationRequest =
    SaveQuestionPointsValidationRequest(
        questionSolutionIdRequest = questionSolutionIdParamsModel.toQuestionSolutionIdRequest(),
        answerPoints = answerPoints,
        questionTotalPoints = questionTotalPoints,
        description = description,
    )
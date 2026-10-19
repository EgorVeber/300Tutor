package com.v_points.app.data.solution.mappers.request

import com.v_points.app.data.solution.models.request.QuestionSolutionIdRequest
import com.v_points.app.domain.solution.models.params_model.QuestionSolutionIdParamsModel

fun QuestionSolutionIdParamsModel.toQuestionSolutionIdRequest(): QuestionSolutionIdRequest =
    QuestionSolutionIdRequest(
        questionId = questionId,
        solutionId = solutionId
    )
package org.v_points.data.solution.mappers.request

import org.v_points.data.solution.models.request.QuestionSolutionIdRequest
import org.v_points.domain.solution.models.params_model.QuestionSolutionIdParamsModel

fun QuestionSolutionIdParamsModel.toQuestionSolutionIdRequest(): QuestionSolutionIdRequest =
    QuestionSolutionIdRequest(
        questionId = questionId,
        solutionId = solutionId
    )
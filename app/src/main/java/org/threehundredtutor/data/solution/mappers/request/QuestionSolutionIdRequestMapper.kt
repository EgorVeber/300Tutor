package org.threehundredtutor.data.solution.mappers.request

import org.threehundredtutor.data.solution.models.request.QuestionSolutionIdRequest
import org.threehundredtutor.domain.solution.models.params_model.QuestionSolutionIdParamsModel

fun QuestionSolutionIdParamsModel.toQuestionSolutionIdRequest(): QuestionSolutionIdRequest =
    QuestionSolutionIdRequest(
        questionId = questionId,
        solutionId = solutionId
    )
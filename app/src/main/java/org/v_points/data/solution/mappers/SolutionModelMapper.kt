package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.solution_response.SolutionResponse
import org.v_points.domain.solution.models.solution_models.SolutionModel
import org.v_points.ui_common.util.orFalse

fun SolutionResponse.toSolutionModel(): SolutionModel =
    SolutionModel(
        answerModelList = answerResponse?.map { answerResponse -> answerResponse.toAnswerModel() }
            .orEmpty(),
        hasAnswersInProcess = hasAnswersInProccess.orFalse()
    )
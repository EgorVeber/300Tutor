package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.solution_response.SolutionResponse
import com.v_points.app.domain.solution.models.solution_models.SolutionModel
import com.v_points.app.ui_common.util.orFalse

fun SolutionResponse.toSolutionModel(): SolutionModel =
    SolutionModel(
        answerModelList = answerResponse?.map { answerResponse -> answerResponse.toAnswerModel() }
            .orEmpty(),
        hasAnswersInProcess = hasAnswersInProccess.orFalse()
    )
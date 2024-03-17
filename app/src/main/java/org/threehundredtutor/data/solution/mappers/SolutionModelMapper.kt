package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.solution_response.SolutionResponse
import org.threehundredtutor.domain.solution.models.solution_models.SolutionModel
import org.threehundredtutor.ui_common.util.orFalse

fun SolutionResponse.toSolutionModel(): SolutionModel =
    SolutionModel(
        answerModelList = answerResponse?.map { answerResponse -> answerResponse.toAnswerModel() }
            .orEmpty(),
        hasAnswersInProcess = hasAnswersInProccess.orFalse()
    )
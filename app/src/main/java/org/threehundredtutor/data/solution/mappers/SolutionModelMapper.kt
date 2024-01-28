package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.solution_response.SolutionResponse
import org.threehundredtutor.domain.solution.models.solution_models.SolutionModel

fun SolutionResponse.toSolutionModel(): SolutionModel =
    SolutionModel(
        answerModelList = answerResponse?.map { answerResponse -> answerResponse.toAnswerModel() }
            .orEmpty(),
        hasAnswersInProcess = hasAnswersInProccess.orFalse()
    )
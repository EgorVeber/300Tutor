package org.threehundredtutor.data.solution.mappers.points

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.points.SolutionPointsResponse
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel

fun SolutionPointsResponse.toSolutionPointsModel(): SolutionPointsModel =
    SolutionPointsModel(
        hasPointsResult = hasPointsResult.orFalse(),
        hasRightAnswerQuestionsCount = hasRightAnswerQuestionsCount.orDefaultNotValidValue(),
        maxTotalPoints = maxTotalPoints.orDefaultNotValidValue(),
        noPointsValidation = noPointsValidation.orFalse(),
        solutionPointsQuestionModel = solutionPointsQuestionResponses?.map { solutionPointsQuestionResponses ->
            solutionPointsQuestionResponses.toSolutionPointsQuestionModel()
        }.orEmpty(),
        questionsCount = questionsCount.orDefaultNotValidValue(),
        solutionId = solutionId ?: throw BadRequestException(),
        studentTotalPoints = studentTotalPoints.orDefaultNotValidValue(),
        validatedQuestionsCount = validatedQuestionsCount.orDefaultNotValidValue(),
        inProccessQuestionsCount = inProccessQuestionsCount.orDefaultNotValidValue(),
    )

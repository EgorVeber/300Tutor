package org.threehundredtutor.data.solution.mappers.points

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.points.SolutionPointsResponse
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType

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
        questionCountNeedCheck = solutionPointsQuestionResponses?.filter {
            AnswerValidationResultType.getType(it.resultType.orEmpty()) == AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF
        }?.size ?: DEFAULT_NOT_VALID_VALUE_INT
    )
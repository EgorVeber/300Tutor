package org.threehundredtutor.data.solution.mappers.points

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.points.SolutionPointsQuestionResponse
import org.threehundredtutor.domain.solution.models.points.SolutionPointsQuestionModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType

fun SolutionPointsQuestionResponse.toSolutionPointsQuestionModel(): SolutionPointsQuestionModel =
    SolutionPointsQuestionModel(
        answerPoints = answerPoints.orDefaultNotValidValue(),
        description = description.orEmpty(),
        isValidated = isValidated.orFalse(),
        questionId = questionId ?: throw BadRequestException(),
        questionTotalPoints = questionTotalPoints.orDefaultNotValidValue(),
        resultType = AnswerValidationResultType.getType(resultType.orEmpty()),
        sourceType = sourceType.orEmpty(),
        validatorId = validatorId.orEmpty(),
    )
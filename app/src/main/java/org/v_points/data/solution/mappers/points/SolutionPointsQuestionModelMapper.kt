package org.v_points.data.solution.mappers.points

import org.v_points.data.solution.models.points.SolutionPointsQuestionResponse
import org.v_points.domain.solution.models.points.SolutionPointsQuestionModel
import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.ui_common.util.BadRequestException
import org.v_points.ui_common.util.orDefaultNotValidValue
import org.v_points.ui_common.util.orFalse

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
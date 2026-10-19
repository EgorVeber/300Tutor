package com.v_points.app.data.solution.mappers.points

import com.v_points.app.data.solution.models.points.SolutionPointsQuestionResponse
import com.v_points.app.domain.solution.models.points.SolutionPointsQuestionModel
import com.v_points.app.domain.solution.models.solution_models.AnswerValidationResultType
import com.v_points.app.ui_common.util.BadRequestException
import com.v_points.app.ui_common.util.orDefaultNotValidValue
import com.v_points.app.ui_common.util.orFalse

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
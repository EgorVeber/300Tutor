package com.v_points.app.data.solution.mappers.points

import com.v_points.app.data.solution.models.points.SolutionPointsResponse
import com.v_points.app.domain.solution.models.points.SolutionPointsModel
import com.v_points.app.domain.solution.models.solution_models.AnswerValidationResultType
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.util.BadRequestException
import com.v_points.app.ui_common.util.orDefaultNotValidValue
import com.v_points.app.ui_common.util.orFalse

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
        inProcessQuestionsCount = inProccessQuestionsCount.orDefaultNotValidValue(),
        questionCountNeedCheck = solutionPointsQuestionResponses?.filter {
            AnswerValidationResultType.getType(it.resultType.orEmpty()) == AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF
        }?.size ?: DEFAULT_NOT_VALID_VALUE_INT
    )
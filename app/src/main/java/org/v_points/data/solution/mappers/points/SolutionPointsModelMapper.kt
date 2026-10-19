package org.v_points.data.solution.mappers.points

import org.v_points.data.solution.models.points.SolutionPointsResponse
import org.v_points.domain.solution.models.points.SolutionPointsModel
import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.v_points.ui_common.util.BadRequestException
import org.v_points.ui_common.util.orDefaultNotValidValue
import org.v_points.ui_common.util.orFalse

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
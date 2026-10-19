package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.solution_response.AnswerResponse
import org.v_points.domain.solution.models.solution_models.AnswerModel
import org.v_points.domain.solution.models.solution_models.AnswerValidationResultType
import org.v_points.domain.solution.models.solution_models.PointsValidationModel
import org.v_points.ui_common.util.orFalse

fun AnswerResponse.toAnswerModel(): AnswerModel =
    AnswerModel(
        answerOrAnswers = answerOrAnswers.orEmpty(),
        isChecked = isChecked.orFalse(),
        pointsValidationModel = pointsValidationResponse?.toPointsValidationModel()
            ?: PointsValidationModel.EMPTY,
        questionId = questionId.orEmpty(),
        questionVersionId = questionVersionId.orEmpty(),
        answerValidationResultType = AnswerValidationResultType.getType(resultType.orEmpty()),
    )
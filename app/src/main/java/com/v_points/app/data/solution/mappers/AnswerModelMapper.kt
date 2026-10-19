package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.solution_response.AnswerResponse
import com.v_points.app.domain.solution.models.solution_models.AnswerModel
import com.v_points.app.domain.solution.models.solution_models.AnswerValidationResultType
import com.v_points.app.domain.solution.models.solution_models.PointsValidationModel
import com.v_points.app.ui_common.util.orFalse

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
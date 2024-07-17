package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.solution_response.AnswerResponse
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.domain.solution.models.solution_models.PointsValidationModel
import org.threehundredtutor.ui_common.util.orFalse

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
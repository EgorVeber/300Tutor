package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.ui_common.util.orFalse

fun QuestionAnswerWithResultBaseApiResponse.toQuestionAnswerWithResultBaseApiModel(): QuestionAnswerWithResultBaseApiModel =
    QuestionAnswerWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answerModel = answerResponse?.toAnswerModel() ?: AnswerModel.EMPTY
    )

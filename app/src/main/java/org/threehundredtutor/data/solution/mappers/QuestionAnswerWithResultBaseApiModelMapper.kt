package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.models.QuestionAnswerWithResultBaseApiModel
import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel

fun QuestionAnswerWithResultBaseApiResponse.toQuestionAnswerWithResultBaseApiModel(): QuestionAnswerWithResultBaseApiModel =
    QuestionAnswerWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answerModel = answerResponse?.toAnswerModel() ?: AnswerModel.EMPTY
    )
package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel

fun QuestionAnswersWithResultBaseApiResponse.toQuestionAnswersWithResultBaseApiModel(): QuestionAnswersWithResultBaseApiModel =
    QuestionAnswersWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answersModel = answerResponse?.answersResponse?.map { answerResponse ->
            answerResponse.toAnswerModel()
        }.orEmpty()
    )

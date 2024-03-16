package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.models.QuestionAnswersWithResultBaseApiModel
import org.threehundredtutor.ui_common.util.orFalse

fun QuestionAnswersWithResultBaseApiResponse.toQuestionAnswersWithResultBaseApiModel(): QuestionAnswersWithResultBaseApiModel =
    QuestionAnswersWithResultBaseApiModel(
        isSucceeded = isSucceeded.orFalse(),
        message = message.orEmpty(),
        answersModel = answerResponse?.answersResponse?.map { answerResponse ->
            answerResponse.toAnswerModel()
        }.orEmpty()
    )

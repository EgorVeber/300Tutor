package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orDefaultNotValidValue
import org.threehundredtutor.data.solution.models.test_response.SelectRightAnswerOrAnswersDataResponse
import org.threehundredtutor.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel

fun SelectRightAnswerOrAnswersDataResponse.toSelectRightAnswerOrAnswersModel(): SelectRightAnswerOrAnswersModel =
    SelectRightAnswerOrAnswersModel(
        answersList = answers?.map { answerXResponse -> answerXResponse.toAnswerSelectRightModel() }
            ?: emptyList(),
        rightAnswersCount = rightAnswersCount.orDefaultNotValidValue(),
        selectRightAnswerTitle = selectRightAnswerTitle.orEmpty(),
    )
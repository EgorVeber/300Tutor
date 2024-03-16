package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.test_response.SelectRightAnswerOrAnswersDataResponse
import org.threehundredtutor.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel
import org.threehundredtutor.ui_common.util.orDefaultNotValidValue

fun SelectRightAnswerOrAnswersDataResponse.toSelectRightAnswerOrAnswersModel(): SelectRightAnswerOrAnswersModel =
    SelectRightAnswerOrAnswersModel(
        answersList = answers?.map { answerXResponse -> answerXResponse.toAnswerSelectRightModel() }
            ?: emptyList(),
        rightAnswersCount = rightAnswersCount.orDefaultNotValidValue(),
        selectRightAnswerTitle = selectRightAnswerTitle.orEmpty(),
    )
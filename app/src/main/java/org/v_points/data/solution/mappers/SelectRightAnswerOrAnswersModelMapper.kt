package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.test_response.SelectRightAnswerOrAnswersDataResponse
import org.v_points.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel
import org.v_points.ui_common.util.orDefaultNotValidValue

fun SelectRightAnswerOrAnswersDataResponse.toSelectRightAnswerOrAnswersModel(): SelectRightAnswerOrAnswersModel =
    SelectRightAnswerOrAnswersModel(
        answersList = answers?.map { answerXResponse -> answerXResponse.toAnswerSelectRightModel() }
            ?: emptyList(),
        rightAnswersCount = rightAnswersCount.orDefaultNotValidValue(),
        selectRightAnswerTitle = selectRightAnswerTitle.orEmpty(),
    )
package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.test_response.SelectRightAnswerOrAnswersDataResponse
import com.v_points.app.domain.solution.models.test_model.SelectRightAnswerOrAnswersModel
import com.v_points.app.ui_common.util.orDefaultNotValidValue

fun SelectRightAnswerOrAnswersDataResponse.toSelectRightAnswerOrAnswersModel(): SelectRightAnswerOrAnswersModel =
    SelectRightAnswerOrAnswersModel(
        answersList = answers?.map { answerXResponse -> answerXResponse.toAnswerSelectRightModel() }
            ?: emptyList(),
        rightAnswersCount = rightAnswersCount.orDefaultNotValidValue(),
        selectRightAnswerTitle = selectRightAnswerTitle.orEmpty(),
    )
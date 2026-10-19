package com.v_points.app.data.solution.mappers

import com.v_points.app.data.solution.models.test_response.AnswerXResponse
import com.v_points.app.domain.solution.models.test_model.AnswerSelectRightModel
import com.v_points.app.ui_common.util.orFalse

fun AnswerXResponse.toAnswerSelectRightModel(): AnswerSelectRightModel =
    AnswerSelectRightModel(
        isRightAnswer = isRightAnswer.orFalse(),
        text = text.orEmpty(),
    )

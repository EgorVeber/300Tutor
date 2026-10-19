package org.v_points.data.solution.mappers

import org.v_points.data.solution.models.test_response.AnswerXResponse
import org.v_points.domain.solution.models.test_model.AnswerSelectRightModel
import org.v_points.ui_common.util.orFalse

fun AnswerXResponse.toAnswerSelectRightModel(): AnswerSelectRightModel =
    AnswerSelectRightModel(
        isRightAnswer = isRightAnswer.orFalse(),
        text = text.orEmpty(),
    )

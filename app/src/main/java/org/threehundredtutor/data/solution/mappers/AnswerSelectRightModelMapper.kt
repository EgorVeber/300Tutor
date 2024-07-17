package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.data.solution.models.test_response.AnswerXResponse
import org.threehundredtutor.domain.solution.models.test_model.AnswerSelectRightModel
import org.threehundredtutor.ui_common.util.orFalse

fun AnswerXResponse.toAnswerSelectRightModel(): AnswerSelectRightModel =
    AnswerSelectRightModel(
        isRightAnswer = isRightAnswer.orFalse(),
        text = text.orEmpty(),
    )

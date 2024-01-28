package org.threehundredtutor.data.solution.mappers

import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.solution.models.test_response.AnswerXResponse
import org.threehundredtutor.domain.solution.models.test_model.AnswerSelectRightModel

fun AnswerXResponse.toAnswerSelectRightModel(): AnswerSelectRightModel =
    AnswerSelectRightModel(
        isRightAnswer = isRightAnswer.orFalse(),
        text = text.orEmpty(),
    )

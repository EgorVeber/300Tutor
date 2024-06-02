package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.domain.solution.models.test_model.AnswerSelectRightModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel

fun AnswerSelectRightModel.toAnswerSelectRightUiModel(
    questionId: String,
    enabled: Boolean,
    checked: Boolean
): SelectRightAnswerUiModel =
    SelectRightAnswerUiModel(
        rightAnswer = isRightAnswer,
        answer = text,
        checked = checked,
        enabled = enabled,
        questionId = questionId,
    )
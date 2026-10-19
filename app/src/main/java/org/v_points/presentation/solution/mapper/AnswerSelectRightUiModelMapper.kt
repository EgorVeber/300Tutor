package org.v_points.presentation.solution.mapper

import org.v_points.domain.solution.models.test_model.AnswerSelectRightModel
import org.v_points.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel

fun AnswerSelectRightModel.toAnswerSelectRightUiModel(
    questionId: String,
    isValidated: Boolean,
    checked: Boolean,
): SelectRightAnswerUiModel =
    SelectRightAnswerUiModel(
        rightAnswer = isRightAnswer,
        answer = text,
        checked = checked,
        isValidated = isValidated,
        questionId = questionId,
    )


fun AnswerSelectRightModel.toAnswerSelectRightUiModel(
    questionId: String,
    isValidated: Boolean,
    checked: Boolean,
    answer:String
): SelectRightAnswerUiModel =
    SelectRightAnswerUiModel(
        rightAnswer = isRightAnswer,
        answer = answer,
        checked = checked,
        isValidated = isValidated,
        questionId = questionId,
    )
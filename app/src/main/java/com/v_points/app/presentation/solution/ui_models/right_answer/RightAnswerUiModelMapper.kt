package com.v_points.app.presentation.solution.ui_models.right_answer

import com.v_points.app.domain.solution.models.test_model.QuestionModel
import com.v_points.app.ui_common.EMPTY_STRING

fun QuestionModel.toRightAnswerUiModel(
    rightAnswersList: List<String>,
    caseInSensitive: Boolean
): RightAnswerUiModel =
    RightAnswerUiModel(
        questionId = questionId,
        title = title,
        caseInSensitive = caseInSensitive,
        rightAnswers = rightAnswersList,
        inputAnswer = EMPTY_STRING
    )

fun QuestionModel.toRightAnswerUiModel(
    rightAnswersList: List<String>,
    caseInSensitive: Boolean,
    inputAnswer: String
): RightAnswerUiModel =
    RightAnswerUiModel(
        questionId = questionId,
        title = title,
        caseInSensitive = caseInSensitive,
        rightAnswers = rightAnswersList,
        inputAnswer = inputAnswer
    )
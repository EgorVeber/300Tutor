package org.v_points.presentation.solution.ui_models.right_answer

import org.v_points.domain.solution.models.test_model.QuestionModel
import org.v_points.ui_common.EMPTY_STRING

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
package org.threehundredtutor.presentation.solution.ui_models.right_answer

import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.ui_common.EMPTY_STRING

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
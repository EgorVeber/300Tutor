package org.threehundredtutor.presentation.solution.ui_models.answer_erros

import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.ui_common.EMPTY_STRING

fun QuestionModel.toAnswerWithErrorsUiModel(
    rightAnswer: String
): AnswerWithErrorsUiModel =
    AnswerWithErrorsUiModel(
        questionId = questionId,
        title = title,
        rightAnswer = rightAnswer,
        inputAnswer = EMPTY_STRING
    )
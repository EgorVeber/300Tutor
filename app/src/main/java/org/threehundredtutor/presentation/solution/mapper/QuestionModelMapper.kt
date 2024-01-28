package org.threehundredtutor.presentation.solution.mapper

import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerUiModel

fun QuestionModel.toRightAnswerUiModel(
    rightAnswersList: List<String>,
    caseInSensitive: Boolean
): RightAnswerUiModel =
    RightAnswerUiModel(
        questionId = questionId,
        title = title,
        testQuestionType = testQuestionType,
        caseInSensitive = caseInSensitive,
        rightAnswers = rightAnswersList
    )

fun QuestionModel.toAnswerWithErrorsUiModel(
    rightAnswer: String
): AnswerWithErrorsUiModel =
    AnswerWithErrorsUiModel(
        questionId = questionId,
        title = title,
        testQuestionType = testQuestionType,
        rightAnswer = rightAnswer,
    )
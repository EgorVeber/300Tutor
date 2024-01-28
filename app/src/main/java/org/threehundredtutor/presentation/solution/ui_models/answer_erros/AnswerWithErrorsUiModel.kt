package org.threehundredtutor.presentation.solution.ui_models.answer_erros

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class AnswerWithErrorsUiModel(
    val questionId: String,
    val title: String,
    val testQuestionType: TestQuestionType,
    val rightAnswer: String,
) : SolutionUiItem


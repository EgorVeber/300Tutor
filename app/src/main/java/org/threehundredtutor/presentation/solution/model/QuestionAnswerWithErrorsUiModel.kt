package org.threehundredtutor.presentation.solution.model

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
data class QuestionAnswerWithErrorsUiModel(
    val solutionId: String,
    var questionId: String,
    val title: String,
    val testQuestionType: TestQuestionType,
    val rightAnswer: String,
) : HtmlItem


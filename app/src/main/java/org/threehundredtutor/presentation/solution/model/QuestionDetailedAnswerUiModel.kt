package org.threehundredtutor.presentation.solution.model

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType

data class QuestionDetailedAnswerUiModel(
    val solutionId: String,
    var questionId: String,
    val testQuestionType: TestQuestionType,
) : HtmlItem


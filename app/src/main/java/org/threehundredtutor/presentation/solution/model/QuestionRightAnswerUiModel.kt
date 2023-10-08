package org.threehundredtutor.presentation.solution.model

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
data class QuestionRightAnswerUiModel(
    var questionId: String,
    val title: String,
    val testQuestionType: TestQuestionType,
    val caseInSensitive: Boolean,
    val rightAnswers: List<String>
) : HtmlItem


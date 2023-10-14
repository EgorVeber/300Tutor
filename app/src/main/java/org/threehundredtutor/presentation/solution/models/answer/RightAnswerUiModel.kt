package org.threehundredtutor.presentation.solution.models.answer

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.presentation.solution.models.SolutionItem

data class RightAnswerUiModel(
    var questionId: String,
    val title: String,
    val testQuestionType: TestQuestionType,
    val caseInSensitive: Boolean,
    val rightAnswers: List<String>
) : SolutionItem


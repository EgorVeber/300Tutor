package org.threehundredtutor.presentation.solution.ui_models.right_answer

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class RightAnswerUiModel(
    val questionId: String,
    val title: String,
    val testQuestionType: TestQuestionType,
    val caseInSensitive: Boolean,
    val rightAnswers: List<String>
) : SolutionUiItem


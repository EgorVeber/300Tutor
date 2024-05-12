package org.threehundredtutor.presentation.solution.ui_models.right_answer

import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class RightAnswerUiModel(
    val questionId: String,
    val title: String,
    val inputAnswer: String,
    val caseInSensitive: Boolean,
    val rightAnswers: List<String>
) : SolutionUiItem
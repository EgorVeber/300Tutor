package org.threehundredtutor.presentation.solution.ui_models.select_right_answer

import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class AnswerSelectRightUiModel(
    val questionId: String,
    val answer: String,
    val rightAnswer: Boolean,
    val checked: Boolean,
    val enabled: Boolean
) : SolutionUiItem
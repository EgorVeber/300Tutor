package org.v_points.presentation.solution.ui_models.select_right_answer

import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class SelectRightAnswerUiModel(
    val questionId: String,
    val answer: String,
    val rightAnswer: Boolean,
    val checked: Boolean,
    val isValidated: Boolean,
) : SolutionUiItem
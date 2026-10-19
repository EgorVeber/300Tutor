package org.v_points.presentation.solution.ui_models.answer_erros

import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class AnswerWithErrorsUiModel(
    val questionId: String,
    val title: String,
    val inputAnswer: String,
    val rightAnswer: String,
) : SolutionUiItem


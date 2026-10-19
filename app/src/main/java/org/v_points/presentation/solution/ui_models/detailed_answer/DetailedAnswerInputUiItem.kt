package org.v_points.presentation.solution.ui_models.detailed_answer

import org.v_points.presentation.solution.ui_models.SolutionUiItem

data class DetailedAnswerInputUiItem(
    val questionId: String,
    val inputAnswer: String,
    val explanationList: List<SolutionUiItem>,
) : SolutionUiItem
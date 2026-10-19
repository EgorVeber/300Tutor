package com.v_points.app.presentation.solution.ui_models.detailed_answer

import com.v_points.app.presentation.solution.ui_models.SolutionUiItem

data class DetailedAnswerInputUiItem(
    val questionId: String,
    val inputAnswer: String,
    val explanationList: List<SolutionUiItem>,
) : SolutionUiItem
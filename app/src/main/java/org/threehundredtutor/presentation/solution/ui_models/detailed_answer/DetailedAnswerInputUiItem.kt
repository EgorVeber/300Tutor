package org.threehundredtutor.presentation.solution.ui_models.detailed_answer

import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem

data class DetailedAnswerInputUiItem(
    val questionId: String,
    val inputAnswer: String,
    val explanationList: List<SolutionUiItem>,
) : SolutionUiItem
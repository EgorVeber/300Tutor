package org.v_points.presentation.solution_history.models

data class StartTestUiModel(
    val testId: String,
    val nameTest: String,
    val questionsCount: Int,
) : SolutionHistoryUiItem
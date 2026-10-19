package com.v_points.app.presentation.solution_history.models

data class StartTestUiModel(
    val testId: String,
    val nameTest: String,
    val questionsCount: Int,
) : SolutionHistoryUiItem
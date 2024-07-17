package org.threehundredtutor.presentation.solution_history.models

data class SolutionHistoryUiModel(
    val solutionId: String,
    val testId: String,
    val nameTest: String,
    val isFinished: Boolean,
    val startTestDate: String,
    val endTestDate: String,

    val pointWithTest: String,
    val testIsSolvedPercent: String,
    val issuesResolvedPercent: String,
    val maxTotalPoints: Int,
    val maxTotalPointsString: String,
    val studentTotalPoints: Int,
    val studentTotalPointsString: String,

    val validatedQuestionsCount: String,
    val hasRightAnswerQuestionsCount: String,
    val questionsCount: Int,
) : SolutionHistoryUiItem
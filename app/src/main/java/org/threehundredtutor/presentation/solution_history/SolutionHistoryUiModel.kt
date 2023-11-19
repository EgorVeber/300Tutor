package org.threehundredtutor.presentation.solution_history

data class SolutionHistoryUiModel(
    val id: String,
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
)
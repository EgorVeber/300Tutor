package org.threehundredtutor.domain.solution_history.models

import org.threehundredtutor.common.EMPTY_STRING

data class SolutionValidationModel(
    val hasRightAnswerQuestionsCount: Int,
    val maxTotalPoints: Int,
    val studentTotalPoints: Int,
    val questionsCount: Int,
    val maxTotalPointsString: String,
    val studentTotalPointsString: String,
    val validatedQuestionsCount: Int,
    val testIsSolvedPercent: Int,
    val issuesResolvedPercent: Int,
) {
    companion object {
        val EMPTY = SolutionValidationModel(
            hasRightAnswerQuestionsCount = -1,
            maxTotalPoints = -1,
            questionsCount = -1,
            studentTotalPoints = -1,
            validatedQuestionsCount = -1,
            testIsSolvedPercent = -1,
            issuesResolvedPercent = -1,
            maxTotalPointsString = EMPTY_STRING,
            studentTotalPointsString = EMPTY_STRING,
        )
    }
}
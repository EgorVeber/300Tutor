package org.threehundredtutor.domain.solution.models.points

data class SolutionPointsModel(
    val hasPointsResult: Boolean,
    val hasRightAnswerQuestionsCount: Int,
    val maxTotalPoints: Int,
    val noPointsValidation: Boolean,
    val solutionPointsQuestionModel: List<SolutionPointsQuestionModel>,
    val questionsCount: Int,
    val solutionId: String,
    val studentTotalPoints: Int,
    val validatedQuestionsCount: Int,
    val inProcessQuestionsCount: Int,
    val questionCountNeedCheck: Int
)
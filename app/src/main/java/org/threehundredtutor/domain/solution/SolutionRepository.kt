package org.threehundredtutor.domain.solution

import org.threehundredtutor.data.solution.models.answer_response.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel

interface SolutionRepository {
    suspend fun getSolution(solutionId: String): TestSolutionQueryModel
    suspend fun checkAnswer(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiResponse
}

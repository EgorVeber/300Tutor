package org.threehundredtutor.data.solution

import org.threehundredtutor.data.solution.mappers.toTestSolutionQueryModel
import org.threehundredtutor.data.solution.models.answer_response.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.data.solution.models.request.AnswerRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionQueryModel
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionRemoteDataSource: SolutionRemoteDataSource,
) : SolutionRepository {
    override suspend fun getSolution(solutionId: String): TestSolutionQueryModel =
        solutionRemoteDataSource.getSolution(solutionId).toTestSolutionQueryModel()

    override suspend fun checkAnswer(
        solutionId: String,
        questionId: String,
        answerOrAnswers: String
    ): QuestionAnswerWithResultBaseApiResponse =
        solutionRemoteDataSource.checkAnswer(
            CheckAnswerRequest(
                solutionId = solutionId,
                AnswerRequest(
                    questionId = questionId,
                    answerOrAnswers = answerOrAnswers
                )
            )
        )
}

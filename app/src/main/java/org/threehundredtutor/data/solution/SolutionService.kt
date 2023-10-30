package org.threehundredtutor.data.solution

import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_CHECK_ANSWER
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE
import org.threehundredtutor.data.solution.models.BaseApiResponse
import org.threehundredtutor.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.data.solution.models.request.SaveQuestionPointsValidationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolutionService {
    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID)
    suspend fun getSolution(@Path("id") id: String): TestSolutionQueryResponse

    @POST(TUTOR_TEST_SOLUTION_CHECK_ANSWER)
    suspend fun checkAnswer(@Body params: CheckAnswerRequest): QuestionAnswerWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE)
    suspend fun resultQuestionsValidationSave(@Body params: SaveQuestionPointsValidationRequest): BaseApiResponse
}

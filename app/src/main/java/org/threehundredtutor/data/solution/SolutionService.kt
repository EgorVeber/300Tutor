package org.threehundredtutor.data.solution

import org.threehundredtutor.data.solution.SolutionApi.ID
import org.threehundredtutor.data.solution.SolutionApi.SOLUTION_ID
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_QUESTION_LIKES_CHANGE
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_CHECK_ANSWER
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_FINISH
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_QUERY_BY_ID_DETAILED
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_POINTS
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE
import org.threehundredtutor.data.solution.SolutionApi.TUTOR_TEST_SOLUTION_START_BY_TEST_ID
import org.threehundredtutor.data.solution.models.BaseApiResponse
import org.threehundredtutor.data.solution.models.QuestionAnswerWithResultBaseApiResponse
import org.threehundredtutor.data.solution.models.QuestionAnswersWithResultBaseApiResponse
import org.threehundredtutor.data.solution.models.TestSolutionQueryDetailedResponse
import org.threehundredtutor.data.solution.models.TestSolutionQueryResponse
import org.threehundredtutor.data.solution.models.finish_test.FinishSolutionRequest
import org.threehundredtutor.data.solution.models.points.SolutionPointsResponse
import org.threehundredtutor.data.solution.models.request.ChangeLikeQuestionRequest
import org.threehundredtutor.data.solution.models.request.CheckAnswerRequest
import org.threehundredtutor.data.solution.models.request.QuestionSolutionIdRequest
import org.threehundredtutor.data.solution.models.request.SaveQuestionPointsValidationRequest
import org.threehundredtutor.data.solution.models.start_test.StartTestRequest
import org.threehundredtutor.data.solution.models.start_test.StartTestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SolutionService {
    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID)
    suspend fun getSolution(@Path(ID) id: String): TestSolutionQueryResponse

    @GET(TUTOR_TEST_SOLUTION_QUERY_BY_ID_DETAILED)
    suspend fun getSolutionDetailed(@Path(ID) id: String): TestSolutionQueryDetailedResponse

    @POST(TUTOR_TEST_SOLUTION_START_BY_TEST_ID)
    suspend fun startByTestId(@Body params: StartTestRequest): StartTestResponse

    @POST(TUTOR_TEST_SOLUTION_FINISH)
    suspend fun finish(@Body params: FinishSolutionRequest): QuestionAnswersWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE)
    suspend fun resultQuestionsValidationSave(@Body params: SaveQuestionPointsValidationRequest): QuestionAnswerWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE)
    suspend fun resultQuestionsValidationRemove(@Body params: QuestionSolutionIdRequest): QuestionAnswerWithResultBaseApiResponse

    @POST(TUTOR_TEST_SOLUTION_CHECK_ANSWER)
    suspend fun checkAnswer(@Body params: CheckAnswerRequest): QuestionAnswerWithResultBaseApiResponse

    @GET(TUTOR_TEST_SOLUTION_RESULT_POINTS)
    suspend fun getResultPoints(@Path(SOLUTION_ID) solutionId: String): SolutionPointsResponse

    @POST(TUTOR_QUESTION_LIKES_CHANGE)
    suspend fun changeLikeQuestion(@Body params: ChangeLikeQuestionRequest): BaseApiResponse
}

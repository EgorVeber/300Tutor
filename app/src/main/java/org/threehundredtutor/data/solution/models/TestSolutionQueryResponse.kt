package org.threehundredtutor.data.solution.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.solution_response.SolutionResponse
import org.threehundredtutor.data.solution.models.test_response.TestResponse

class TestSolutionQueryResponse(
    @SerializedName("solutionId")
    val solutionId: String?,
    @SerializedName("hasCuratorValidation")
    val hasCuratorValidation: Boolean?,
    @SerializedName("canCheckSingleQuestion")
    val canCheckSingleQuestion: Boolean?,
    @SerializedName("studentGroupId")
    val studentGroupId: String?,
    @SerializedName("studentId")
    val studentId: String?,
    @SerializedName("startedOnUtc")
    val startedOnUtc: String?,
    @SerializedName("finishedOnUtc")
    val finishedOnUtc: String?,
    @SerializedName("isFinished")
    val isFinished: Boolean?,
    @SerializedName("solution")
    val solutionResponse: SolutionResponse?,
    @SerializedName("test")
    val testResponse: TestResponse?
)

class StartTestResponse(
    @SerializedName("isSucceeded")
    val isSucceeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("responseObject")
    val testSolutionQueryResponse: TestSolutionQueryResponse?,
)
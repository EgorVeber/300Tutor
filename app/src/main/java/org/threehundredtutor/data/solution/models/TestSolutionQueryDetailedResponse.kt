package org.threehundredtutor.data.solution.models

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.test_response.TestResponseDetailed

class TestSolutionQueryDetailedResponse(
    @SerializedName("solutionId")
    val solutionId: String?,
    @SerializedName("hasCuratorValidation")
    val hasCuratorValidation: Boolean?,
    @SerializedName("hasAnswersInProccess")
    val hasAnswersInProccess: Boolean?,
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
    @SerializedName("test")
    val testResponseDetailed: TestResponseDetailed?
)
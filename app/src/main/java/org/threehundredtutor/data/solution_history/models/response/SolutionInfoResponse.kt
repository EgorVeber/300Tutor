package org.threehundredtutor.data.solution_history.models.response

import com.google.gson.annotations.SerializedName

class SolutionInfoResponse(
    @SerializedName("finishedOnUtc")
    val finishedOnUtc: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFinished")
    val isFinished: Boolean?,
    @SerializedName("startedOnUtc")
    val startedOnUtc: String?,
    @SerializedName("testId")
    val testId: String?,
    @SerializedName("testName")
    val testName: String?
)
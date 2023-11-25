package org.threehundredtutor.data.solution_history.models.request

import com.google.gson.annotations.SerializedName

class SearchSolutionHistoryRequest(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("isFinished")
    val isFinished: Boolean?,
    @SerializedName("offSet")
    val offSet: Int,
    @SerializedName("testId")
    val testId: String?
)
package org.threehundredtutor.data.solution_history.models.response

import com.google.gson.annotations.SerializedName

class SolutionItemResponse(
    @SerializedName("solution")
    val solutionInfoResponse: SolutionInfoResponse?,
    @SerializedName("systemValidation")
    val solutionValidationResponse: SolutionValidationResponse?,
    @SerializedName("unreadMessagesCount")
    val unreadMessagesCount: Int?
)
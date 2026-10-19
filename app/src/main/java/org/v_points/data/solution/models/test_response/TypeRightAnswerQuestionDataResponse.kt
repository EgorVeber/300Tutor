package org.v_points.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class TypeRightAnswerQuestionDataResponse(
    @SerializedName("caseInSensitive")
    val caseInSensitive: Boolean?,
    @SerializedName("rightAnswers")
    val rightAnswers: List<String>?
)
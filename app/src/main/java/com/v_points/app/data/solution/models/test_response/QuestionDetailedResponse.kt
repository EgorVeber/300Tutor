package com.v_points.app.data.solution.models.test_response

import com.google.gson.annotations.SerializedName
import com.v_points.app.data.solution.models.solution_response.AnswerResponse

class QuestionDetailedResponse(
    @SerializedName("answerWithValidation")
    val answerWithValidation: AnswerResponse?,
    @SerializedName("isQuestionLikedByStudent")
    val isQuestionLikedByStudent: Boolean?,
    @SerializedName("question")
    val question: QuestionResponse?
)
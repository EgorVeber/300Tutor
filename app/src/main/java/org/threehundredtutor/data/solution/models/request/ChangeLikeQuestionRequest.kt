package org.threehundredtutor.data.solution.models.request

import com.google.gson.annotations.SerializedName

class ChangeLikeQuestionRequest(
    @SerializedName("questionId") val questionId: String,
    @SerializedName("hasLike") val hasLike: Boolean
)

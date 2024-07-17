package org.threehundredtutor.data.favorites


import com.google.gson.annotations.SerializedName
import org.threehundredtutor.data.solution.models.test_response.QuestionResponse

class FavoritesResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("list") val list: List<QuestionResponse>?,
    @SerializedName("offSet") val offSet: Int?,
    @SerializedName("totalCount") val totalCount: Int?
)
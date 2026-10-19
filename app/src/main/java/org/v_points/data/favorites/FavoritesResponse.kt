package org.v_points.data.favorites


import com.google.gson.annotations.SerializedName
import org.v_points.data.solution.models.test_response.QuestionResponse

class FavoritesResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("list") val list: List<QuestionResponse>?,
    @SerializedName("offSet") val offSet: Int?,
    @SerializedName("totalCount") val totalCount: Int?
)
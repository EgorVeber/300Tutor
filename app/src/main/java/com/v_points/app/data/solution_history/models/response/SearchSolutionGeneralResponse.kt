package com.v_points.app.data.solution_history.models.response

import com.google.gson.annotations.SerializedName

class SearchSolutionGeneralResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val solutionItems: List<SolutionItemResponse>?,
    @SerializedName("offSet")
    val offSet: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?
)
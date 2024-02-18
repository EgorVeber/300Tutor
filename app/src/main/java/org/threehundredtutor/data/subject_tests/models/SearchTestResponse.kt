package org.threehundredtutor.data.subject_tests.models

import com.google.gson.annotations.SerializedName

class SearchTestResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offSet")
    val offSet: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?,
    @SerializedName("list")
    val searchTestInfoResponseList: List<SearchTestInfoResponse>?,
)
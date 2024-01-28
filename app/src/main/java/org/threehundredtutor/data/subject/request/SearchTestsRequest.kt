package org.threehundredtutor.data.subject.request

import com.google.gson.annotations.SerializedName

class SearchTestsRequest(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("isGlobal")
    val isGlobal: Boolean,
    @SerializedName("offSet")
    val offSet: Int,
    @SerializedName("q")
    val q: String?,
    @SerializedName("subjectIds")
    val subjectIds: List<String>
)
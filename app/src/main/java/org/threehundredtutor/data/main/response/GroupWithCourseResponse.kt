package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class GroupWithCourseResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<GroupWithCourseProgressResponse>?,
    @SerializedName("offSet")
    val offSet: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?
)
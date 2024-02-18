package org.threehundredtutor.data.main.request

import com.google.gson.annotations.SerializedName

class GroupWithCourseRequest(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offSet")
    val offSet: Int,
    @SerializedName("q")
    val q: String?,
    @SerializedName("studentId")
    val studentId: String
)
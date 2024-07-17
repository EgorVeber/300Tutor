package org.threehundredtutor.data.subject_detailed.models

import com.google.gson.annotations.SerializedName

class RootResponse(
    @SerializedName("children")
    val children: List<RootResponse>?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("workSpaceId")
    val workSpaceId: String?
)
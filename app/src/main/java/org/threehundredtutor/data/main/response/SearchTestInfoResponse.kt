package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class SearchTestInfoResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("isGlobal")
    val isGlobal: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("questionsCount")
    val questionsCount: Int?,
    @SerializedName("subject")
    val subject: SubjectResponse?
)
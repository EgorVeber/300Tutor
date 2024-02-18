package org.threehundredtutor.data.main.response

import com.google.gson.annotations.SerializedName

class SubjectResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("alias")
    val alias: String?
)
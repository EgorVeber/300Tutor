package org.threehundredtutor.data.subject.response

import com.google.gson.annotations.SerializedName

class SubjectResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
package org.threehundredtutor.data.subject_tests.models

import com.google.gson.annotations.SerializedName

class SubjectResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("alias")
    val alias: String?
)
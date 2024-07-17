package org.threehundredtutor.data.subject_tests.models

import com.google.gson.annotations.SerializedName

class IconResponse(
    @SerializedName("fileId")
    val fileId: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("serverPath")
    val serverPath: String?,
    @SerializedName("setId")
    val setId: String?
)
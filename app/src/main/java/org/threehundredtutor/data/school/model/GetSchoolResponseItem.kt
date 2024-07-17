package org.threehundredtutor.data.school.model


import com.google.gson.annotations.SerializedName


data class GetSchoolResponseItem(
    @SerializedName("hostUrl")
    val hostUrl: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
package org.threehundredtutor.data.subject_workspace.model


import com.google.gson.annotations.SerializedName

class QuestionsTypeAndCountResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("type")
    val type: String?
)
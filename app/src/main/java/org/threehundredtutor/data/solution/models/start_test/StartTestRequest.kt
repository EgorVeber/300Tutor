package org.threehundredtutor.data.solution.models.start_test

import com.google.gson.annotations.SerializedName

class StartTestRequest(
    @SerializedName("canCheckSingleQuestion")
    val canCheckSingleQuestion: Boolean, // Не понятно на что влияет
    @SerializedName("studentGroupId")
    val studentGroupId: String?,
    @SerializedName("testId")
    val testId: String
)

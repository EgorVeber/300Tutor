package org.threehundredtutor.data.solution.models

import com.google.gson.annotations.SerializedName

data class StartTestParams(
    @SerializedName("canCheckSingleQuestion")
    val canCheckSingleQuestion: Boolean,
    @SerializedName("studentGroupId")
    val studentGroupId: String?,
    @SerializedName("testId")
    val testId: String
)
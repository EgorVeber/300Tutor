package org.v_points.data.solution.models.directory

import com.google.gson.annotations.SerializedName

class StartTestDirectoryRequest(
    @SerializedName("workSpaceId")
    val workSpaceId: String,
    @SerializedName("directoryId")
    val directoryId: String,
    @SerializedName("filter")
    val testQuestionTypeRequest: TestQuestionTypeRequest,
    @SerializedName("useFilter")
    val useFilter: Boolean,
    @SerializedName("canCheckSingleQuestion")
    val canCheckSingleQuestion: Boolean?,//Можно ли проверять каждый вопрос в тесте на правильность
)
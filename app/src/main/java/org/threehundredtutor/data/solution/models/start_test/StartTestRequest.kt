package org.threehundredtutor.data.solution.models.start_test

import com.google.gson.annotations.SerializedName

class StartTestRequest(
    @SerializedName("canCheckSingleQuestion")
    //Можно ли проверять каждый вопрос в тесте на правильность
    val canCheckSingleQuestion: Boolean = true,// TODO пока всегда true узнать откуда его брать и сделать обработку
    @SerializedName("studentGroupId")
    val studentGroupId: String? = null,
    @SerializedName("testId")
    val testId: String
)

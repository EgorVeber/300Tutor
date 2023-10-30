package org.threehundredtutor.data.solution.models.test_response

import com.google.gson.annotations.SerializedName

class QuestionResponse(
    @SerializedName("answerExplanationMarkUp")
    val answerExplanationMarkUp: String?,
    @SerializedName("answerExplanationMarkUpMobile")
    val answerExplanationMarkUpMobile: String?,
    @SerializedName("helpBodyMarkUp")
    val helpBodyMarkUp: String?,
    @SerializedName("helpBodyMarkUpMobile")
    val helpBodyMarkUpMobile: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("selectRightAnswerOrAnswersData")
    val selectRightAnswerOrAnswersDataResponse: SelectRightAnswerOrAnswersDataResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("titleBodyMarkUp")
    val titleBodyMarkUp: String?,
    @SerializedName("titleBodyMarkUpMobile")
    val titleBodyMarkUpMobile: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("typeAnswerWithErrorsData")
    val typeAnswerWithErrorsDataResponse: TypeAnswerWithErrorsDataResponse?,
    @SerializedName("typeRightAnswerQuestionData")
    val typeRightAnswerQuestionDataResponse: TypeRightAnswerQuestionDataResponse?,
    @SerializedName("versionId")
    val versionId: String?
)
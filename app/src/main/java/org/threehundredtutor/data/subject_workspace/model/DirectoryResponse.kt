package org.threehundredtutor.data.subject_workspace.model

import com.google.gson.annotations.SerializedName

class DirectoryResponse(
    @SerializedName("children")
    val childDirectoriesList: List<DirectoryResponse>?,
    @SerializedName("id")
    val directoryId: String?,
    @SerializedName("htmlPage")
    val htmlPageResponse: HtmlPageResponse?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("questionTypeCounts")
    val questionsTypeAndCountList: List<QuestionsTypeAndCountResponse>?
)
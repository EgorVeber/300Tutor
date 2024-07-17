package org.threehundredtutor.data.subject_workspace.model

import com.google.gson.annotations.SerializedName

class HtmlPageResponse(
    @SerializedName("html")
    val html: String?,
    @SerializedName("htmlMobile")
    val htmlMobile: String?,
    @SerializedName("id")
    val htmlPageId: String?,
    @SerializedName("name")
    val name: String?
)
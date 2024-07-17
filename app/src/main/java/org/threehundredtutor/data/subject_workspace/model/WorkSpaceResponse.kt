package org.threehundredtutor.data.subject_workspace.model

import com.google.gson.annotations.SerializedName

class WorkSpaceResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val workSpaceId: String?,
    @SerializedName("name")
    val name: String?
)
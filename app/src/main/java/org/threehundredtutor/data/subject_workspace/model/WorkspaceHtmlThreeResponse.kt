package org.threehundredtutor.data.subject_workspace.model

import com.google.gson.annotations.SerializedName

class WorkspaceHtmlThreeResponse(
    @SerializedName("directory")
    val directoryResponse: DirectoryResponse?,
    @SerializedName("workSpace")
    val workSpaceResponse: WorkSpaceResponse?
)
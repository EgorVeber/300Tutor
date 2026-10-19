package com.v_points.app.data.subject_workspace

import com.v_points.app.data.subject_workspace.SubjectWorkspaceApi.TUTOR_WORKSPACE_HTML_THREE
import com.v_points.app.data.subject_workspace.model.WorkspaceHtmlThreeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SubjectWorkspaceService {
    @GET(TUTOR_WORKSPACE_HTML_THREE)
    suspend fun getWorkSpace(@Query("id") workspaceId: String): WorkspaceHtmlThreeResponse
}
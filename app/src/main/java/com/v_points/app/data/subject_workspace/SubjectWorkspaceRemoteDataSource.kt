package com.v_points.app.data.subject_workspace

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.subject_workspace.model.WorkspaceHtmlThreeResponse
import javax.inject.Inject

class SubjectWorkspaceRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = {
        serviceGeneratorProvider.getService(SubjectWorkspaceService::class)
    }

    suspend fun getWorkSpace(workspaceId: String): WorkspaceHtmlThreeResponse =
        service().getWorkSpace(workspaceId = workspaceId)
}

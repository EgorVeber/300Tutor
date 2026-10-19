package org.v_points.data.subject_workspace

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.subject_workspace.model.WorkspaceHtmlThreeResponse
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

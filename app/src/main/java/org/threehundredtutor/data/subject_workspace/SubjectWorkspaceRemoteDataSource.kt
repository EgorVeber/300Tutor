package org.threehundredtutor.data.subject_workspace

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.subject_workspace.model.WorkspaceHtmlThreeResponse
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

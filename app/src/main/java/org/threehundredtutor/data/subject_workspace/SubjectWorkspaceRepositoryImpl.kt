package org.threehundredtutor.data.subject_workspace

import org.threehundredtutor.data.subject_workspace.mappers.toWorkspaceHtmlThreeModel
import org.threehundredtutor.domain.subject_workspace.SubjectWorkspaceRepository
import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.domain.subject_workspace.models.WorkspaceHtmlThreeModel
import javax.inject.Inject

class SubjectWorkspaceRepositoryImpl @Inject constructor(
    private val subjectWorkspaceRemoteDataSource: SubjectWorkspaceRemoteDataSource,
    private val subjectWorkspaceLocalDataSource: SubjectWorkspaceLocalDataSource,
) : SubjectWorkspaceRepository {

    override suspend fun getWorkSpace(workspaceId: String): WorkspaceHtmlThreeModel =
        subjectWorkspaceRemoteDataSource.getWorkSpace(workspaceId = workspaceId)
            .toWorkspaceHtmlThreeModel().also { workspaceHtmlThreeModel ->
                subjectWorkspaceLocalDataSource.setDirectory(workspaceHtmlThreeModel)
            }

    override fun getWorkSpaceId(): String =
        subjectWorkspaceLocalDataSource.getWorkspaceId()

    override fun findDirectoryById(workspaceId: String): DirectoryModel? {
        val cache = subjectWorkspaceLocalDataSource.getWorkspaceHtmlThreeModel()
        return if (cache != null) {
            findDirectoryRecursion(cache.directoryModel, workspaceId)
        } else {
            null
        }
    }

    private fun findDirectoryRecursion(
        directoryModel: DirectoryModel,
        directoryId: String
    ): DirectoryModel? {
        if (directoryModel.directoryId == directoryId) return directoryModel

        directoryModel.childDirectoriesList.forEach { internalDirectoryModel ->
            if (internalDirectoryModel.directoryId == directoryId) return internalDirectoryModel
            else {
                val temp = findDirectoryRecursion(internalDirectoryModel, directoryId)
                if (temp != null) return temp
            }
        }
        return null
    }
}

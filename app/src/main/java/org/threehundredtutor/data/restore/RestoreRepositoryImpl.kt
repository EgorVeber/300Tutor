package org.threehundredtutor.data.restore

import org.threehundredtutor.domain.restore.RestorePasswordModel
import org.threehundredtutor.domain.restore.RestoreRepository
import javax.inject.Inject

class RestoreRepositoryImpl @Inject constructor(
    private val restoreRemoteDataSource: RestoreRemoteDataSource,
) : RestoreRepository {
    override suspend fun forgotStart(email: String): RestorePasswordModel =
        restoreRemoteDataSource.forgotStart(RestoreRequest(email)).toRestorePasswordModel()
}
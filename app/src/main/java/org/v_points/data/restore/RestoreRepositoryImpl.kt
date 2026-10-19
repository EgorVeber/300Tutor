package org.v_points.data.restore

import org.v_points.domain.restore.RestorePasswordModel
import org.v_points.domain.restore.RestoreRepository
import javax.inject.Inject

class RestoreRepositoryImpl @Inject constructor(
    private val restoreRemoteDataSource: RestoreRemoteDataSource,
) : RestoreRepository {
    override suspend fun forgotStart(email: String): RestorePasswordModel =
        restoreRemoteDataSource.forgotStart(RestoreRequest(email)).toRestorePasswordModel()
}
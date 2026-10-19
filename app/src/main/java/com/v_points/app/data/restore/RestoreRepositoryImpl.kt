package com.v_points.app.data.restore

import com.v_points.app.domain.restore.RestorePasswordModel
import com.v_points.app.domain.restore.RestoreRepository
import javax.inject.Inject

class RestoreRepositoryImpl @Inject constructor(
    private val restoreRemoteDataSource: RestoreRemoteDataSource,
) : RestoreRepository {
    override suspend fun forgotStart(email: String): RestorePasswordModel =
        restoreRemoteDataSource.forgotStart(RestoreRequest(email)).toRestorePasswordModel()
}
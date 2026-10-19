package org.v_points.domain.restore

interface RestoreRepository {
    suspend fun forgotStart(email: String): RestorePasswordModel
}

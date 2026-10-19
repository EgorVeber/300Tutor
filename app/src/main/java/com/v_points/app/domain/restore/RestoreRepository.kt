package com.v_points.app.domain.restore

interface RestoreRepository {
    suspend fun forgotStart(email: String): RestorePasswordModel
}

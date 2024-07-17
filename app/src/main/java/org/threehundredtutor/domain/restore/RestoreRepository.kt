package org.threehundredtutor.domain.restore

interface RestoreRepository {
    suspend fun forgotStart(email: String): RestorePasswordModel
}

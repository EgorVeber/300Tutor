package org.v_points.domain.restore

import javax.inject.Inject

class ForgotStartUseCase @Inject constructor(
    private val repository: RestoreRepository
) {
    suspend operator fun invoke(email: String): RestorePasswordModel = repository.forgotStart(email)
}
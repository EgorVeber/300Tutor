package com.v_points.app.domain.authorization

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(loginParamsModel: LoginParamsModel): LoginModel =
        repository.login(loginParamsModel)
}
package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.points.SolutionPointsModel
import javax.inject.Inject

class GetPointsUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionId: String): SolutionPointsModel =
        solutionRepository.getResultPoints(solutionId = solutionId)
}

package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.points.SolutionPointsModel
import javax.inject.Inject

class GetPointsUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionId: String): SolutionPointsModel =
        solutionRepository.getResultPoints(solutionId = solutionId)
}

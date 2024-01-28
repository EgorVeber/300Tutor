package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.points.SolutionPointsModel
import javax.inject.Inject

class GetPointsUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionId: String): SolutionPointsModel =
        solutionRepository.getResultPoints(solutionId = solutionId)
}

package org.threehundredtutor.domain.solution

import javax.inject.Inject

class GetSolutionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(solutionIdInt: String) = solutionRepository.getSolution(solutionIdInt)
}

package org.threehundredtutor.data.solution

import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class SolutionRepositoryImpl @Inject constructor(
    private val solutionRemoteDataSource: SolutionRemoteDataSource,
) : SolutionRepository {
    override suspend fun getSolution(solutionId: String): TestSolutionQueryResponse =
        solutionRemoteDataSource.getSolution(solutionId)
}

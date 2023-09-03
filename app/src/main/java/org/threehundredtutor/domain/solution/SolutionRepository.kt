package org.threehundredtutor.domain.solution

import org.threehundredtutor.data.solution.TestSolutionQueryResponse

interface SolutionRepository {
    suspend fun getSolution(solutionId: String): TestSolutionQueryResponse
}

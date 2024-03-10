package org.threehundredtutor.domain.solution_history

import org.threehundredtutor.domain.solution_history.models.SearchSolutionGeneralModel

interface SolutionHistoryRepository {
    suspend fun searchSolutionByTestId(testId: String): SearchSolutionGeneralModel
    suspend fun searchSolution(force: Boolean): SearchSolutionGeneralModel
}

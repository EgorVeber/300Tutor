package org.v_points.domain.solution_history

import org.v_points.domain.solution_history.models.SearchSolutionGeneralModel

interface SolutionHistoryRepository {
    suspend fun searchSolution(force: Boolean): SearchSolutionGeneralModel
}

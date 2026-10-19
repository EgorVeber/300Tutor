package com.v_points.app.domain.solution_history

import com.v_points.app.domain.solution_history.models.SearchSolutionGeneralModel

interface SolutionHistoryRepository {
    suspend fun searchSolution(force: Boolean): SearchSolutionGeneralModel
}

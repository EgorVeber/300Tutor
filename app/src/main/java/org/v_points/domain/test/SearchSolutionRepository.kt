package org.v_points.domain.test

import org.v_points.domain.solution_history.models.SearchSolutionGeneralModel

interface SearchSolutionRepository {
    suspend fun searchSolutionByTestId(testId: String): SearchSolutionGeneralModel
}

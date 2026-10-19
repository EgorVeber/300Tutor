package com.v_points.app.domain.test

import com.v_points.app.domain.solution_history.models.SearchSolutionGeneralModel

interface SearchSolutionRepository {
    suspend fun searchSolutionByTestId(testId: String): SearchSolutionGeneralModel
}

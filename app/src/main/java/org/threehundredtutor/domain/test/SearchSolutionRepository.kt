package org.threehundredtutor.domain.test

import org.threehundredtutor.domain.solution_history.models.SearchSolutionGeneralModel

interface SearchSolutionRepository {
    suspend fun searchSolutionByTestId(testId: String): SearchSolutionGeneralModel
}

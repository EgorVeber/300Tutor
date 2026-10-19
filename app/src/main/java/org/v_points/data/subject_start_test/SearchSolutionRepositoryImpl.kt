package org.v_points.data.subject_start_test

import org.v_points.data.solution_history.mappers.toSearchSolutionGeneralModel
import org.v_points.data.solution_history.models.request.SearchSolutionHistoryRequest
import org.v_points.domain.solution_history.models.SearchSolutionGeneralModel
import org.v_points.domain.test.SearchSolutionRepository
import javax.inject.Inject

class SearchSolutionRepositoryImpl @Inject constructor(
    private val searchSolutionRemoteDataSource: SearchSolutionRemoteDataSource,
) : SearchSolutionRepository {

    override suspend fun searchSolutionByTestId(
        testId: String
    ): SearchSolutionGeneralModel = searchSolutionRemoteDataSource.searchSolution(
        SearchSolutionHistoryRequest(
            count = null,
            isFinished = null,
            offSet = 0,
            testId = testId
        )
    ).toSearchSolutionGeneralModel()
}

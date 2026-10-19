package com.v_points.app.data.subject_start_test

import com.v_points.app.data.solution_history.mappers.toSearchSolutionGeneralModel
import com.v_points.app.data.solution_history.models.request.SearchSolutionHistoryRequest
import com.v_points.app.domain.solution_history.models.SearchSolutionGeneralModel
import com.v_points.app.domain.test.SearchSolutionRepository
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

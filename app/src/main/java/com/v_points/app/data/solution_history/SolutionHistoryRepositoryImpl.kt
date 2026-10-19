package com.v_points.app.data.solution_history

import com.v_points.app.data.solution_history.mappers.toSearchSolutionGeneralModel
import com.v_points.app.data.solution_history.models.request.SearchSolutionHistoryRequest
import com.v_points.app.domain.solution_history.SolutionHistoryRepository
import com.v_points.app.domain.solution_history.models.SearchSolutionGeneralModel
import javax.inject.Inject

class SolutionHistoryRepositoryImpl @Inject constructor(
    private val solutionHistoryRemoteDataSource: SolutionHistoryRemoteDataSource,
    private val solutionHistoryLocalDataSource: SolutionHistoryLocalDataSource,
) : SolutionHistoryRepository {
    override suspend fun searchSolution(
        force: Boolean,
    ): SearchSolutionGeneralModel {
        return if (force) {
            solutionHistoryLocalDataSource.setSolution(
                solutionHistoryRemoteDataSource.searchSolution(
                    SearchSolutionHistoryRequest(
                        count = 100,// TODO пагинация
                        isFinished = null,
                        offSet = 0,
                        testId = null
                    )
                ).toSearchSolutionGeneralModel()
            )
            solutionHistoryLocalDataSource.getSolution()
        } else {
            solutionHistoryLocalDataSource.getSolution()
        }
    }
}

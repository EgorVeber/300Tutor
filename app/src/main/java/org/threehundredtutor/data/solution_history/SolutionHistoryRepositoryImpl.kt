package org.threehundredtutor.data.solution_history

import org.threehundredtutor.data.solution_history.mappers.toSearchSolutionGeneralModel
import org.threehundredtutor.domain.solution_history.SolutionHistoryRepository
import org.threehundredtutor.domain.solution_history.models.SearchSolutionGeneralModel
import javax.inject.Inject

class SolutionHistoryRepositoryImpl @Inject constructor(
    private val solutionHistoryRemoteDataSource: SolutionHistoryRemoteDataSource,
    private val solutionHistoryLocalDataSource: SolutionHistoryLocalDataSource,
) : SolutionHistoryRepository {
    override suspend fun searchSolution(force: Boolean): SearchSolutionGeneralModel {
        return if (force) {
            solutionHistoryLocalDataSource.setSolution(
                solutionHistoryRemoteDataSource.searchSolution().toSearchSolutionGeneralModel()
            )
            solutionHistoryLocalDataSource.getSolution()
        } else {
            solutionHistoryLocalDataSource.getSolution()
        }
    }
}

package org.threehundredtutor.domain.test

import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import javax.inject.Inject

class SearchSolutionUseCase @Inject constructor(
    private val searchSolutionRepository: SearchSolutionRepository
) {
    suspend operator fun invoke(
        testId: String,
    ): Pair<List<SolutionHistoryItemModel>, Boolean> {
        val solutionHistory = searchSolutionRepository.searchSolutionByTestId(
            testId = testId
        ).solutionItems
        return solutionHistory to (solutionHistory.all { it.solutionInfoModel.isFinished })
    }
}
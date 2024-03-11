package org.threehundredtutor.domain.test

import org.threehundredtutor.domain.solution_history.SolutionHistoryRepository
import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import javax.inject.Inject

class SearchSolutionUseCase @Inject constructor(
    private val solutionHistoryRepository: SolutionHistoryRepository
) {
    suspend operator fun invoke(
        testId: String,
    ): Pair<List<SolutionHistoryItemModel>, Boolean> {
        val solutionHistory = solutionHistoryRepository.searchSolutionByTestId(
            testId = testId
        ).solutionItems
        return solutionHistory to (solutionHistory.all { it.solutionInfoModel.isFinished })
    }
}
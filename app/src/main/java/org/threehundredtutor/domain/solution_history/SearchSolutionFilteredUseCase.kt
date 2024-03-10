package org.threehundredtutor.domain.solution_history

import org.threehundredtutor.domain.solution_history.models.SolutionHistoryItemModel
import javax.inject.Inject

class SearchSolutionFilteredUseCase @Inject constructor(
    private val solutionHistoryRepository: SolutionHistoryRepository
) {
    suspend operator fun invoke(
        force: Boolean,
        solutionHistoryFilter: SolutionHistoryFilter,
    ): List<SolutionHistoryItemModel> {
        val solutionHistoryItems = solutionHistoryRepository.searchSolution(
            force = force,
        ).solutionItems
        return when (solutionHistoryFilter) {
            SolutionHistoryFilter.COMPLETED -> solutionHistoryItems.filter { it.solutionInfoModel.isFinished }
            SolutionHistoryFilter.NOT_COMPLETED -> solutionHistoryItems.filter { !it.solutionInfoModel.isFinished }
            else -> solutionHistoryItems
        }
    }
}

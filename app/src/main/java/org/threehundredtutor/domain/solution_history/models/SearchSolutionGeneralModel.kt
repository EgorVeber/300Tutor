package org.threehundredtutor.domain.solution_history.models

data class SearchSolutionGeneralModel(
    val count: Int,
    val solutionItems: List<SolutionHistoryItemModel>,
    val offSet: Int,
    val totalCount: Int
) {
    companion object {
        val EMPTY = SearchSolutionGeneralModel(
            count = 0,
            solutionItems = emptyList(),
            offSet = 0,
            totalCount = 0
        )
    }
}
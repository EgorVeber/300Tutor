package org.threehundredtutor.data.solution_history

import org.threehundredtutor.domain.solution_history.models.SearchSolutionGeneralModel

class SolutionHistoryLocalDataSource {

    private var searchSolutionGeneralModel: SearchSolutionGeneralModel =
        SearchSolutionGeneralModel.EMPTY

    fun setSolution(searchSolutionGeneralModel: SearchSolutionGeneralModel) {
        this.searchSolutionGeneralModel = searchSolutionGeneralModel
    }

    fun getSolution() = searchSolutionGeneralModel
}
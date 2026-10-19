package com.v_points.app.data.solution_history

import com.v_points.app.domain.solution_history.models.SearchSolutionGeneralModel

class SolutionHistoryLocalDataSource {

    private var searchSolutionGeneralModel: SearchSolutionGeneralModel =
        SearchSolutionGeneralModel.EMPTY

    fun setSolution(searchSolutionGeneralModel: SearchSolutionGeneralModel) {
        this.searchSolutionGeneralModel = searchSolutionGeneralModel
    }

    fun getSolution() = searchSolutionGeneralModel
}
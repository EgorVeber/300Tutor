package org.v_points.data.solution_history.mappers

import org.v_points.data.solution_history.models.response.SearchSolutionGeneralResponse
import org.v_points.domain.solution_history.models.SearchSolutionGeneralModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun SearchSolutionGeneralResponse.toSearchSolutionGeneralModel(): SearchSolutionGeneralModel =
    SearchSolutionGeneralModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        solutionItems = solutionItems?.map { solutionItemResponse ->
            solutionItemResponse.toSolutionHistoryItemModel()
        }.orEmpty(),
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT
    )
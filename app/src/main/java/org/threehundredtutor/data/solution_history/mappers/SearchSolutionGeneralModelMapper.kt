package org.threehundredtutor.data.solution_history.mappers

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.data.solution_history.models.response.SearchSolutionGeneralResponse
import org.threehundredtutor.domain.solution_history.models.SearchSolutionGeneralModel

fun SearchSolutionGeneralResponse.toSearchSolutionGeneralModel(): SearchSolutionGeneralModel =
    SearchSolutionGeneralModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        solutionItems = solutionItems?.map { solutionItemResponse ->
            solutionItemResponse.toSolutionHistoryItemModel()
        }.orEmpty(),
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT
    )
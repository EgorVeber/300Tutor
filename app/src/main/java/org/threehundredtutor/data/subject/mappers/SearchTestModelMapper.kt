package org.threehundredtutor.data.subject.mappers

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.data.subject.response.SearchTestResponse
import org.threehundredtutor.domain.subject.models.SearchTestModel

fun SearchTestResponse.toSearchTestModel(): SearchTestModel =
    SearchTestModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        searchTestInfoModelList = searchTestInfoResponseList?.map { searchTestInfoResponse ->
            searchTestInfoResponse.toSearchTestInfoModel()
        } ?: emptyList(),
    )
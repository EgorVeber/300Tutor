package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.subject_tests.models.SearchTestResponse
import org.threehundredtutor.domain.subject_tests.models.SearchTestModel
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun SearchTestResponse.toSearchTestModel(): SearchTestModel =
    SearchTestModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        searchTestInfoModelList = searchTestInfoResponseList?.map { searchTestInfoResponse ->
            searchTestInfoResponse.toSearchTestInfoModel()
        } ?: emptyList(),
    )
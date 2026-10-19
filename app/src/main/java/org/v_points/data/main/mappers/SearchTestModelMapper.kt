package org.v_points.data.main.mappers

import org.v_points.data.subject_tests.models.SearchTestResponse
import org.v_points.domain.subject_tests.models.SearchTestModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun SearchTestResponse.toSearchTestModel(): SearchTestModel =
    SearchTestModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        searchTestInfoModelList = searchTestInfoResponseList?.map { searchTestInfoResponse ->
            searchTestInfoResponse.toSearchTestInfoModel()
        } ?: emptyList(),
    )
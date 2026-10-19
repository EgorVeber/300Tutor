package com.v_points.app.data.main.mappers

import com.v_points.app.data.subject_tests.models.SearchTestResponse
import com.v_points.app.domain.subject_tests.models.SearchTestModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT

fun SearchTestResponse.toSearchTestModel(): SearchTestModel =
    SearchTestModel(
        count = count ?: DEFAULT_NOT_VALID_VALUE_INT,
        offSet = offSet ?: DEFAULT_NOT_VALID_VALUE_INT,
        totalCount = totalCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        searchTestInfoModelList = searchTestInfoResponseList?.map { searchTestInfoResponse ->
            searchTestInfoResponse.toSearchTestInfoModel()
        } ?: emptyList(),
    )
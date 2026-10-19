package com.v_points.app.data.main.mappers

import com.v_points.app.data.subject_tests.models.SearchTestInfoResponse
import com.v_points.app.domain.subject_tests.models.SearchTestInfoModel
import com.v_points.app.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.ServerException
import com.v_points.app.ui_common.util.orFalse

fun SearchTestInfoResponse.toSearchTestInfoModel(): SearchTestInfoModel =
    SearchTestInfoModel(
        description = description ?: EMPTY_STRING,
        id = id ?: throw ServerException(),
        isActive = isActive.orFalse(),
        isGlobal = isGlobal.orFalse(),
        name = name ?: EMPTY_STRING,
        questionsCount = questionsCount ?: DEFAULT_NOT_VALID_VALUE_INT,
        subject = subject?.toSubjectModel() ?: throw ServerException()
    )
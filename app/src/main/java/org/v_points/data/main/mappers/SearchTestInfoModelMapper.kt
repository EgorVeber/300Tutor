package org.v_points.data.main.mappers

import org.v_points.data.subject_tests.models.SearchTestInfoResponse
import org.v_points.domain.subject_tests.models.SearchTestInfoModel
import org.v_points.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.ServerException
import org.v_points.ui_common.util.orFalse

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
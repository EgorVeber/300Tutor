package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.subject_tests.models.SearchTestInfoResponse
import org.threehundredtutor.domain.subject_tests.models.SearchTestInfoModel
import org.threehundredtutor.ui_common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.util.ServerException
import org.threehundredtutor.ui_common.util.orFalse

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
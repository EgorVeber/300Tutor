package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.common.orFalse
import org.threehundredtutor.data.main.response.SearchTestInfoResponse
import org.threehundredtutor.domain.main.models.SearchTestInfoModel

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
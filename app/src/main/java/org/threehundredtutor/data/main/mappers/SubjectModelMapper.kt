package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.main.response.SubjectResponse
import org.threehundredtutor.domain.main.models.SubjectModel

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(
        id = id ?: throw ServerException(),
        name = name ?: EMPTY_STRING,
        alias = alias ?: throw ServerException()
    )
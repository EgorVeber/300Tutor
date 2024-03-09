package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.subject_tests.models.SubjectResponse
import org.threehundredtutor.domain.subject_tests.models.SubjectModel

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(
        id = id ?: throw ServerException(),
        name = name ?: EMPTY_STRING,
        alias = alias ?: throw ServerException()
    )
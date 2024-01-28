package org.threehundredtutor.data.subject.mappers

import org.threehundredtutor.base.network.ServerException
import org.threehundredtutor.common.EMPTY_STRING
import org.threehundredtutor.data.subject.response.SubjectResponse
import org.threehundredtutor.domain.subject.models.SubjectModel

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(id = id ?: throw ServerException(), name = name ?: EMPTY_STRING)
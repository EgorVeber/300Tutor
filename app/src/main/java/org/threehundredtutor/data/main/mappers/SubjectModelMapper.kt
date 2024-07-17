package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.subject_tests.models.SubjectResponse
import org.threehundredtutor.data.subject_tests.models.toIconModel
import org.threehundredtutor.domain.subject_tests.models.IconModel
import org.threehundredtutor.domain.subject_tests.models.SubjectModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import org.threehundredtutor.ui_common.util.ServerException

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(
        id = id ?: throw ServerException(),
        name = name ?: EMPTY_STRING,
        alias = alias ?: throw ServerException(),
        iconModel = iconResponse?.toIconModel() ?: IconModel.empty
    )
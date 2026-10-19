package org.v_points.data.main.mappers

import org.v_points.data.subject_tests.models.SubjectResponse
import org.v_points.data.subject_tests.models.toIconModel
import org.v_points.domain.subject_tests.models.IconModel
import org.v_points.domain.subject_tests.models.SubjectModel
import org.v_points.ui_common.EMPTY_STRING
import org.v_points.ui_common.util.ServerException

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(
        id = id ?: throw ServerException(),
        name = name ?: EMPTY_STRING,
        alias = alias ?: throw ServerException(),
        iconModel = iconResponse?.toIconModel() ?: IconModel.empty
    )
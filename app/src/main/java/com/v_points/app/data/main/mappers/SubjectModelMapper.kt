package com.v_points.app.data.main.mappers

import com.v_points.app.data.subject_tests.models.SubjectResponse
import com.v_points.app.data.subject_tests.models.toIconModel
import com.v_points.app.domain.subject_tests.models.IconModel
import com.v_points.app.domain.subject_tests.models.SubjectModel
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.util.ServerException

fun SubjectResponse.toSubjectModel(): SubjectModel =
    SubjectModel(
        id = id ?: throw ServerException(),
        name = name ?: EMPTY_STRING,
        alias = alias ?: throw ServerException(),
        iconModel = iconResponse?.toIconModel() ?: IconModel.empty
    )
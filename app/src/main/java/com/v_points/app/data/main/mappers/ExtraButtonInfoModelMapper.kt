package com.v_points.app.data.main.mappers

import com.v_points.app.data.main.response.ExtraButtonInfoResponse
import com.v_points.app.domain.main.models.ExtraButtonInfoModel
import com.v_points.app.ui_common.util.BadRequestException

fun ExtraButtonInfoResponse.toExtraButtonInfoModel(): ExtraButtonInfoModel = ExtraButtonInfoModel(
    text = text ?: throw BadRequestException(),
    color = color ?: throw BadRequestException(),
    link = link ?: throw BadRequestException()
)
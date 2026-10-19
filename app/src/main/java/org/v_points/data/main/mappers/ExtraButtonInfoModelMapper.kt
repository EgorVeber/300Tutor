package org.v_points.data.main.mappers

import org.v_points.data.main.response.ExtraButtonInfoResponse
import org.v_points.domain.main.models.ExtraButtonInfoModel
import org.v_points.ui_common.util.BadRequestException

fun ExtraButtonInfoResponse.toExtraButtonInfoModel(): ExtraButtonInfoModel = ExtraButtonInfoModel(
    text = text ?: throw BadRequestException(),
    color = color ?: throw BadRequestException(),
    link = link ?: throw BadRequestException()
)
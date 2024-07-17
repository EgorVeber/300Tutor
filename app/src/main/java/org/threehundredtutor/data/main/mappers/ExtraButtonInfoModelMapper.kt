package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.data.main.response.ExtraButtonInfoResponse
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.ui_common.util.BadRequestException

fun ExtraButtonInfoResponse.toExtraButtonInfoModel(): ExtraButtonInfoModel = ExtraButtonInfoModel(
    text = text ?: throw BadRequestException(),
    color = color ?: throw BadRequestException(),
    link = link ?: throw BadRequestException()
)
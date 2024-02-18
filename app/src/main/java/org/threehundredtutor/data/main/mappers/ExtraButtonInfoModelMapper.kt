package org.threehundredtutor.data.main.mappers

import org.threehundredtutor.base.network.BadRequestException
import org.threehundredtutor.data.main.response.ExtraButtonInfoResponse
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel

fun ExtraButtonInfoResponse.toExtraButtonInfoModel(): ExtraButtonInfoModel = ExtraButtonInfoModel(
    text = text ?: throw BadRequestException(),
    color = color ?: throw BadRequestException(),
    link = link ?: throw BadRequestException()
)